
package com.example.myislamicapp.data.network;

import static com.example.myislamicapp.data.network.ApiConstant.QURAN_ONLINE_BASEURL;
import static com.example.myislamicapp.data.network.ApiConstant.QURAN_TRANSLATE_BASEURL;
import static com.example.myislamicapp.data.network.ApiConstant.RADIO_BASEURL;
import static com.example.myislamicapp.data.network.ApiConstant.TAFSEER_BASE_URL;
import static com.example.myislamicapp.data.utils.Constant.PRAYER_BASE_URL;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit instance;


    private static Retrofit getInstance() {
        if (instance != null) {
            instance = null;
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        instance = new Retrofit.Builder()
                .baseUrl(QURAN_ONLINE_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return instance;
    }

    private static Retrofit getTafseer() {
        if (instance != null) {
            instance = null;
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        instance = new Retrofit.Builder()
                .baseUrl(TAFSEER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return instance;
    }


    private static Retrofit getTranslateRetrofitInstance() {
        if (instance != null) {
            instance = null;
        }
        instance = new Retrofit.Builder()
                .baseUrl(QURAN_TRANSLATE_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

    private static Retrofit getRadioRetrofit() {
        if (instance != null) {
            instance = null;
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        instance = new Retrofit.Builder()
                .baseUrl(RADIO_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return instance;
    }

    private static Retrofit getPrayerRetrofit() {
        if (instance != null) {
            instance = null;
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        instance = new Retrofit.Builder()
                .baseUrl(PRAYER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return instance;
    }

    public ApiService provideApiService() {
        return getInstance().create(ApiService.class);
    }

    public ApiService provideTranslateApiService() {
        return getTranslateRetrofitInstance().create(ApiService.class);
    }

    public static ApiService provideRadioApi() {
        return getRadioRetrofit().create(ApiService.class);
    }

    public static ApiService getApi() {
        return getPrayerRetrofit().create(ApiService.class);
    }

    public static ApiService getTafseerApi() {
        return getTafseer().create(ApiService.class);
    }


}
