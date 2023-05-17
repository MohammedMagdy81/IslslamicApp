package com.example.myislamicapp.ui.fragments.quranOnline;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.network.ApiClient;
import com.example.myislamicapp.data.network.ApiService;
import com.example.myislamicapp.data.pojo.quranResponse.Surah;
import com.example.myislamicapp.data.pojo.quranResponse.SurahResponse;
import com.example.myislamicapp.data.pojo.translate.SurahDetailsResponse;
import com.example.myislamicapp.data.utils.Network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuranOnlineViewModel extends AndroidViewModel {

    MutableLiveData<String> messageError = new MutableLiveData<>();
    MutableLiveData<SurahResponse> data = new MutableLiveData<>();
    MutableLiveData<Boolean> showProgress = new MutableLiveData<>();


    ApiClient apiClient;
    ApiService apiService;
    ApiService apiServiceTranslate;


    public QuranOnlineViewModel(@NonNull Application application) {
        super(application);
        apiClient = new ApiClient();
        apiService = apiClient.provideApiService();
        apiServiceTranslate = apiClient.provideTranslateApiService();

    }

    public void getSurah() {
        showProgress.setValue(true);
        apiService.getSurah().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                    showProgress.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                messageError.setValue(t.getLocalizedMessage());
                showProgress.setValue(false);
            }
        });

    }



    public LiveData<SurahDetailsResponse> getSurahDetails(String lang, int suraId) {
        MutableLiveData<SurahDetailsResponse> data = new MutableLiveData<>();
        apiServiceTranslate.getSurahDetail(lang, suraId).enqueue(new Callback<SurahDetailsResponse>() {
            @Override
            public void onResponse(Call<SurahDetailsResponse> call, Response<SurahDetailsResponse> response) {
                if (response.isSuccessful()) {

                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SurahDetailsResponse> call, Throwable t) {
                messageError.setValue(t.getLocalizedMessage());
            }
        });
        return data;
    }

}
