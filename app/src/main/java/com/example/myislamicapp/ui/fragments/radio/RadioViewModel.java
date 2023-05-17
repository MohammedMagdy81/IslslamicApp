package com.example.myislamicapp.ui.fragments.radio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.network.ApiClient;
import com.example.myislamicapp.data.network.ApiService;
import com.example.myislamicapp.data.pojo.radio.RadioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioViewModel extends ViewModel {

    ApiService apiService;

    MutableLiveData<RadioResponse> responseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> messageError = new MutableLiveData<>();


    public RadioViewModel() {

        apiService = ApiClient.provideRadioApi();
    }

    public void getRadioChanel() {
        apiService.getRadioChannel().enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                if (response.isSuccessful()) {
                    responseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {
                messageError.setValue(t.getLocalizedMessage());
            }
        });
    }

}
