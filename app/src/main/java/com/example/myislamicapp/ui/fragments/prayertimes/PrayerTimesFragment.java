package com.example.myislamicapp.ui.fragments.prayertimes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myislamicapp.data.prayersNotifications.AzanUtils;
import com.example.myislamicapp.databinding.FragmentPrayerTimesBinding;
import com.example.myislamicapp.ui.adapters.PrayerTimeListAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class PrayerTimesFragment extends Fragment {
    private FragmentPrayerTimesBinding binding;
    private PrayerTimeListAdapter adapter;
    private PrayerTimesViewModel viewModel;
    Calendar calendar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PrayerTimeListAdapter();
        viewModel = new ViewModelProvider(this).get(PrayerTimesViewModel.class);
        calendar = Calendar.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPrayerTimesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setPrayerTimings(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
        setupAdapter();
        initDatePicker();
        AzanUtils.registerPrayerTimes(requireContext().getApplicationContext());


    }


    private void initDatePicker() {


        binding.datePicker
                .init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), (datePicker, year, month, day) -> {
                    viewModel.setPrayerTimings( day, month, year);
                    Log.d("current Day :", day + " - " + month + " - " + year);
                });

    }

    private void setupAdapter() {
        binding.prayersRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.prayersRv.setHasFixedSize(true);
        binding.prayersRv.setAdapter(adapter);
        viewModel.getPrayerTiming().observe(getViewLifecycleOwner(), prayerTimings -> {
            adapter.setPrayerTiming(prayerTimings);
            adapter.notifyDataSetChanged();
        });

    }
}