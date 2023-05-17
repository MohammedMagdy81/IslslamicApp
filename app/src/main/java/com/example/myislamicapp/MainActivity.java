package com.example.myislamicapp;

import static com.example.myislamicapp.data.utils.Constant.REGISTER_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.example.myislamicapp.data.database.QuranDatabase;
import com.example.myislamicapp.data.prayersNotifications.AzanUtils;
import com.example.myislamicapp.data.prayersNotifications.RegisterPrayerTimeWorker;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AzanUtils.registerPrayerTimes(this);

    }


}