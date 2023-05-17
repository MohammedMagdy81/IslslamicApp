package com.example.myislamicapp.ui.fragments.prayertimes;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myislamicapp.data.network.ApiClient;
import com.example.myislamicapp.data.pojo.prayer.PrayerApiResponse;
import com.example.myislamicapp.data.pojo.prayer.PrayerTiming;
import com.example.myislamicapp.data.pojo.prayer.Timings;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends AndroidViewModel {
    private MutableLiveData<PrayerApiResponse> dataResponse;

    private MutableLiveData<ArrayList<PrayerTiming>> prayerTiming;

    public PrayerTimesViewModel(Application application) {
        super(application);
        prayerTiming = new MutableLiveData<>();
        dataResponse = new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<PrayerTiming>> getPrayerTiming() {
        return prayerTiming;
    }

    public MutableLiveData<PrayerApiResponse> getDataResponse() {
        return dataResponse;
    }


    Call<PrayerApiResponse> getPrayers(String city, String country, int method, int month, int year) {
        return ApiClient.getApi().getPrayerTime(city, country, method, month, year);
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

    public void getCurrentPrayerTiming(int month, int year) {
        getPrayers("Cairo", "Egypt", 3, month + 1, year)
                .enqueue(new Callback<PrayerApiResponse>() {
                    @Override
                    public void onResponse(Call<PrayerApiResponse> call, Response<PrayerApiResponse> response) {
                        if (response.isSuccessful() && response.body().getData() != null) {
                            dataResponse.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PrayerApiResponse> call, Throwable t) {
                        Log.d("PrayersTime : ", t.getLocalizedMessage());
                    }
                });
    }

    public void setPrayerTimings(int day, int month, int year) {
        getPrayers("Cairo", "Egypt", 3, month + 1, year)
                .enqueue(new Callback<PrayerApiResponse>() {
                    @Override
                    public void onResponse(Call<PrayerApiResponse> call, Response<PrayerApiResponse> response) {
                        if (response.isSuccessful() && response.body().getData() != null) {
                            Timings timings = response.body().getData().get(day - 1).getTimings();
                            ArrayList<PrayerTiming> prayerTimings = convertFromTimings(timings);
                            prayerTiming.setValue(prayerTimings);
                            dataResponse.setValue(response.body());
                            Log.d("response", "onResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PrayerApiResponse> call, Throwable t) {
                        Log.d("PrayersTime : ", t.getLocalizedMessage());
                    }
                });
    }

}
