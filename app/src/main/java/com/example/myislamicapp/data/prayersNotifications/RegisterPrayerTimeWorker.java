package com.example.myislamicapp.data.prayersNotifications;

import static com.example.myislamicapp.data.utils.Constant.AZAN_CONTENT_KEY;
import static com.example.myislamicapp.data.utils.Constant.AZAN_TITLE_KEY;

import android.content.Context;
import android.util.Log;

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
import java.util.Objects;
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
            int month = calendar.get(Calendar.MONTH) + 1;

            // call this api again to register data of prayer time . . .
            Response<PrayerApiResponse> timesResponse = ApiClient.getApi().getPrayerTime
                    ("Cairo", "Egypt", 3, month, year).execute();
            if (timesResponse.isSuccessful()) {
                List<DataItem> data = timesResponse.body().getData();// prayers of all month
                for (int i = 0; i < data.size(); i++) {
                    DataItem dataItem = data.get(i);
                    Timings timings = dataItem.getTimings();
                    ArrayList<PrayerTiming> myTimings = convertFromTimings(timings);
                    int day = i + 1;

                    myTimings.forEach(prayers -> {

                        String prayerTag = "" + year + "/" + month + "/" + day + " " + prayers.getPrayerArabicName();
                        long delay = calculatePrayerDelay(year, month, day, prayers);
                        if (delay > 0) {
                            // put data that sended to Azan Work
                            Data input = new Data.Builder()
                                    .putString(AZAN_TITLE_KEY, prayers.getPrayerArabicName())
                                    .putString(AZAN_CONTENT_KEY, "حان الان موعد الصلاة ")
                                    .build();


                            OneTimeWorkRequest mRequest = new OneTimeWorkRequest
                                    .Builder(AzanNotificationWorker.class)
                                    .addTag(prayerTag)
                                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                                    .setInputData(input)
                                    .build();

                            // tell work manger to do this request
                            WorkManager.getInstance(getApplicationContext())
                                    .enqueueUniqueWork(prayerTag,
                                            ExistingWorkPolicy.REPLACE, mRequest);

                        }
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

//    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
//        String pattern = "yyyy/MM/dd HH:mm";
//        DecimalFormat format = new DecimalFormat("00");
//        String time = prayerTiming.getPrayerTime().split(" ")[0];
//        String prayerDate = "" + year + "/" + format.format(month) + "/" + format.format(day) + " " + time;
//        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
//        try {
//            Date date = dateFormat.parse(prayerDate);
//            long currentTime = System.currentTimeMillis();
//            return Objects.requireNonNull(date).getTime() - currentTime;
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return -1;
//        }
//
//    }

    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
        String pattern = "yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = "" + year + "/" + decimalFormat.format(month) + "/" + decimalFormat.format(day) + " " + time;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

        try {
            Date date = format.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            Log.d("TAG", "calculatePrayerDelay: " + date.toString());
            Log.d("TAG", "calculatePrayerDelay: diff = " + (date.getTime() - currentTime) + " " + date.getTime());
            return (date.getTime() - currentTime);
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
