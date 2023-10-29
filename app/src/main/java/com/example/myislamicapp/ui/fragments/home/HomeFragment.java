package com.example.myislamicapp.ui.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.prayer.PrayerApiResponse;
import com.example.myislamicapp.databinding.FragmentHomeBinding;
import com.example.myislamicapp.ui.fragments.prayertimes.PrayerTimesViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PrayerTimesViewModel viewModel;
    Handler handler = new Handler();
    Calendar calendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PrayerTimesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater);
        binding.homeCardQuranOffline.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_fehresFragment);
        });
        binding.homeCardQuranOnline.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_quranOnlineFragment);
        });
        binding.homeCardAzkar.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_homeFragment_to_azkarFragment);
        });
        binding.homeCardRadio.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_homeFragment_to_radioFragment);
        });
        binding.homeCardPrayerTimes.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_homeFragment_to_prayerTimesFragment);
        });
        binding.homeCardAhadeeth.setOnClickListener(view -> {

            NavHostFragment.findNavController(this).navigate(
                    R.id.action_homeFragment_to_fortyAhadeethFragment
            );

        });
        binding.homeCardTafseer.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_homeFragment_to_tafseerFragment);
        });
        binding.homeCardTasbeeh.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_homeFragment_to_tasbeehFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCurrentPrayerTiming(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
                binding.textCurrentTime.setText(currentTime);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
        viewModel.getDataResponse().observe(getViewLifecycleOwner(), prayerApiResponse -> {
            Log.d("prayerApi", "onViewCreated: " + prayerApiResponse);
            binding.textCurrentDay.setText(prayerApiResponse.getData()
                    .get(calendar.get(Calendar.DAY_OF_MONTH) - 1)
                    .getDate().getGregorian()
                    .getDate()
                    +
                    " - " + prayerApiResponse.getData()
                    .get(calendar.get(Calendar.DAY_OF_MONTH) - 1)
                    .getDate()
                    .getHijri().getWeekday().getAr());

            binding.textDateHijri.setText(
                    prayerApiResponse.getData().get(calendar.get(Calendar.DAY_OF_MONTH) - 1)
                            .getDate().getHijri()
                            .getDay() + " - " +
                            prayerApiResponse.getData().get(calendar.get(Calendar.DAY_OF_MONTH) - 1)
                                    .getDate().getHijri()
                                    .getMonth().getAr()

            );
        });

    }


}