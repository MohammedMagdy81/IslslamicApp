package com.example.myislamicapp.ui.fragments.tafseer;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.network.ApiClient;
import com.example.myislamicapp.data.network.ApiService;
import com.example.myislamicapp.data.pojo.tafseer.SuraTafseerResponse;
import com.example.myislamicapp.data.pojo.tafseer.Tafseer;
import com.example.myislamicapp.data.pojo.tafseer.TafseerResponseItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TafseerViewModel extends ViewModel {

    ApiService apiService;
    MutableLiveData<Tafseer> ayahTafseer = new MutableLiveData<>();
    MutableLiveData<SuraTafseerResponse> ayah = new MutableLiveData<>();
    MutableLiveData<List<TafseerResponseItem>> data = new MutableLiveData<>();

    ArrayList<TafseerResponseItem> dataList = new ArrayList<>();
    MutableLiveData<Boolean> showProgress = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isTextShow = new MutableLiveData<>(false);

    public TafseerViewModel() {
        apiService = ApiClient.getTafseerApi();
    }

    public void getAllTafseer() {
        showProgress.setValue(true);
        apiService.getAllTafseer().enqueue(new Callback<List<TafseerResponseItem>>() {
            @Override
            public void onResponse(Call<List<TafseerResponseItem>> call, Response<List<TafseerResponseItem>> response) {
                showProgress.setValue(false);
                if (!dataList.isEmpty()) {
                    dataList.clear();
                }
                for (TafseerResponseItem item : response.body()) {
                    if (item.getLanguage().equals("ar") || item.getLanguage().equals("en")) {
                        dataList.add(item);
                    }
                }
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(dataList);
                }
            }

            @Override
            public void onFailure(Call<List<TafseerResponseItem>> call, Throwable t) {
                showProgress.setValue(false);
                Log.d("Response", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void getTafseerAyah(int tafseerId, int suraNum, int ayahNum) {
        showProgress.setValue(true);
        apiService.getAyahTafseer(tafseerId, suraNum, ayahNum)
                .enqueue(new Callback<Tafseer>() {
                    @Override
                    public void onResponse(Call<Tafseer> call, Response<Tafseer> response) {
                        if (response.isSuccessful()) {
                            showProgress.setValue(false);
                            isTextShow.setValue(true);
                            ayahTafseer.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Tafseer> call, Throwable t) {
                        isTextShow.setValue(false);
                        showProgress.setValue(false);
                    }
                });

    }

    public void getSpecificAyah(int suraNumber, int ayahNumber) {
        showProgress.setValue(true);
        apiService.getSpecificAyah(suraNumber, ayahNumber)
                .enqueue(new Callback<SuraTafseerResponse>() {
                    @Override
                    public void onResponse(Call<SuraTafseerResponse> call, Response<SuraTafseerResponse> response) {
                        showProgress.setValue(false);
                        ayah.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<SuraTafseerResponse> call, Throwable t) {
                        showProgress.setValue(false);
                    }
                });
    }

}














