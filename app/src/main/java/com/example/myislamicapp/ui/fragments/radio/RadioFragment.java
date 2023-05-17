package com.example.myislamicapp.ui.fragments.radio;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.data.pojo.radio.RadioChannel;
import com.example.myislamicapp.databinding.FragmentRadioBinding;
import com.example.myislamicapp.ui.adapters.RadioChannelAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class RadioFragment extends Fragment {

    private FragmentRadioBinding binding;
    private RadioChannelAdapter adapter;
    private RadioViewModel viewModel;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RadioViewModel.class);
        adapter = new RadioChannelAdapter();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadioBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();

    }

    private void setupAdapter() {
        binding.radioRv.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        binding.radioRv.setAdapter(adapter);
        binding.radioRv.setHasFixedSize(true);
        viewModel.getRadioChanel();
        viewModel.responseMutableLiveData.observe(getViewLifecycleOwner(), radioResponse -> {
            adapter.setRadioChannels((ArrayList<RadioChannel>) radioResponse.radios);
        });

        adapter.onPlayRadioClick = (position, url) -> {
            playRadio(url);
        };
        adapter.onStopRadioClick = (position, url) -> {
            stopRadio();
        };
    }

    private void stopRadio() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.reset();
    }

    private void playRadio(String url) {
        stopRadio();
        try {
            mediaPlayer.setDataSource(requireContext(), Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(MediaPlayer::start);

    }


}













