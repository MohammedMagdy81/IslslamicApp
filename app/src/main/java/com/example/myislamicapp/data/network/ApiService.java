package com.example.myislamicapp.data.network;

import com.example.myislamicapp.data.pojo.prayer.PrayerApiResponse;
import com.example.myislamicapp.data.pojo.quranResponse.SurahResponse;
import com.example.myislamicapp.data.pojo.radio.RadioResponse;
import com.example.myislamicapp.data.pojo.tafseer.SuraTafseerResponse;
import com.example.myislamicapp.data.pojo.tafseer.Tafseer;
import com.example.myislamicapp.data.pojo.tafseer.TafseerResponseItem;
import com.example.myislamicapp.data.pojo.translate.SurahDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("surah")
    Call<SurahResponse> getSurah();

    @GET("sura/{language}/{id}")
    Call<SurahDetailsResponse> getSurahDetail(@Path("language") String lang,
                                              @Path("id") int surahId);

    @GET("radios/radio_arabic.json")
    Call<RadioResponse> getRadioChannel();

    @GET("calendarByCity")
    Call<PrayerApiResponse> getPrayerTime(@Query("city") String city,
                                          @Query("country") String country,
                                          @Query("method") int method,
                                          @Query("month") int month,
                                          @Query("year") int year);

    @GET("tafseer")
    Call<List<TafseerResponseItem>> getAllTafseer();

    @GET("tafseer/{tafseer_id}/{sura_number}/{ayah_number}")
    Call<Tafseer> getAyahTafseer(@Path("tafseer_id") int tafseerId,
                                 @Path("sura_number") int suraNumber,
                                 @Path("ayah_number") int ayahNum);

    @GET("quran/{sura_number}/{ayah_number}")
    Call<SuraTafseerResponse> getSpecificAyah(@Path("sura_number") int suraNumber,
                                              @Path("ayah_number") int ayahNum);
}






