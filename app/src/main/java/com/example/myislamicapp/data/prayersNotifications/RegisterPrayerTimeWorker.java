package com.example.myislamicapp.data.prayersNotifications;

import static com.example.myislamicapp.data.utils.Constant.AZAN_CONTENT_KEY;
import static com.example.myislamicapp.data.utils.Constant.AZAN_TITLE_KEY;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myislamicapp.data.network.ApiClient;
import com.example.myislamicapp.data.pojo.prayer.DataItem;
import com.example.myislamicapp.data.pojo.prayer.PrayerApiResponse;
import com.example.myislamicapp.data.pojo.prayer.PrayerTiming;
import com.example.myislamicapp.data.pojo.prayer.Timings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimeWorker extends Worker {

    public RegisterPrayerTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;


            Response<PrayerApiResponse> response = ApiClient.getApi()
                    .getPrayerTime("Cairo", "Egypt", 3, month, year).execute();
            if (response.isSuccessful()) {
                List<DataItem> data = response.body().getData();
                for (int i = 0; i < data.size(); i++) {
                    DataItem dataItem = data.get(i);
                    Timings timings = dataItem.getTimings();
                    ArrayList<PrayerTiming> prayerTimings = convertFromTimings(timings);
                    int day = i + 1;
                    prayerTimings.forEach(prayerTiming -> {
                        String prayerTag = "" + year + "/" + month + "/" + day + " " + prayerTiming.getPrayerArabicName();
                        Data input = new Data.Builder()
                                .putString(AZAN_TITLE_KEY, prayerTiming.getPrayerArabicName())
                                .putString(AZAN_CONTENT_KEY, "حان الان موعد الصلاة")
                                .build();

                        OneTimeWorkRequest request = new
                                OneTimeWorkRequest.Builder(AzanNotificationWorker.class)
                                .setInputData(input)
                                .setInitialDelay(calculatePrayerDelay(prayerTiming
                                        , year, month, (day)), TimeUnit.MILLISECONDS)
                                .addTag(prayerTag)
                                .build();
                        WorkManager.getInstance(getApplicationContext())
                                .enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE, request);
                    });
                }


                return Result.success();
            } else {
                return Result.failure();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }

    }

    private long calculatePrayerDelay(PrayerTiming prayerTiming, int year, int month, int day) {
        String pattern = "yyyy/MM/dd HH:mm";
        DecimalFormat format = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = "" + year + "/" + format.format(month) + "/" + format.format(day) + " " + time;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            Date date = dateFormat.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            return Math.abs(date.getTime() - currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public ArrayList<PrayerTiming> convertFromTimings(Timings timings) {
        ArrayList<PrayerTiming> result = new ArrayList<>();
        result.add(new PrayerTiming("صلاة الفجر", "Fajr", timings.getFajr()));
        result.add(new PrayerTiming("شروق الشمس", "Sunrise", timings.getSunrise()));
        result.add(new PrayerTiming("صلاة الظهر", "Dhuhr", timings.getDhuhr()));
        result.add(new PrayerTiming("صلاة العصر", "Asr", timings.getAsr()));
        result.add(new PrayerTiming("صلاة المغرب", "Maghrib", timings.getMaghrib()));
        result.add(new PrayerTiming("صلاة العشاء", "Isha", timings.getIsha()));
        return result;
    }

}
