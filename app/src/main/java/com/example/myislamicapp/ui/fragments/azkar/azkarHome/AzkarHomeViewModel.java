package com.example.myislamicapp.ui.fragments.azkar.azkarHome;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myislamicapp.data.azkarProviders.AzkarProviders;
import com.example.myislamicapp.data.pojo.azkar.Zekr;
import com.example.myislamicapp.data.pojo.azkar.ZekrType;

import java.util.ArrayList;
import java.util.HashSet;

public class AzkarHomeViewModel extends AndroidViewModel {
    public AzkarHomeViewModel(@NonNull Application application) {
        super(application);
    }

    public HashSet<ZekrType> getAzkarTypes(){
        return AzkarProviders.getAzkarTypes(getApplication().getApplicationContext());
    }

    public ArrayList<Zekr> getAzkarByType(String zekrType){
        return AzkarProviders.getAzkarByType(getApplication().getApplicationContext(),zekrType);
    }

}
