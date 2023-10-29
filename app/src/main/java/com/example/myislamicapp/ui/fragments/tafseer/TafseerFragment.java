package com.example.myislamicapp.ui.fragments.tafseer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.tafseer.TafseerResponseItem;
import com.example.myislamicapp.databinding.FragmentPrayerTimesBinding;
import com.example.myislamicapp.databinding.FragmentTafseerBinding;
import com.example.myislamicapp.ui.adapters.TafseerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TafseerFragment extends Fragment {

    private FragmentTafseerBinding binding;
    private TafseerListAdapter adapter;
    private TafseerViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TafseerListAdapter(new ArrayList<>(), this);
        viewModel = new ViewModelProvider(this).get(TafseerViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTafseerBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAllTafseer();
        setupAdapter();
    }

    private void setupAdapter() {
        binding.rvTafseer.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTafseer.setHasFixedSize(true);
        binding.rvTafseer.setAdapter(adapter);

        viewModel.data.observe(getViewLifecycleOwner(), tafseerResponseItems -> {
            adapter.setResponseItems((ArrayList<TafseerResponseItem>) tafseerResponseItems);
        });
        viewModel.showProgress.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                binding.progressTafseer.setVisibility(View.VISIBLE);
            } else {
                binding.progressTafseer.setVisibility(View.GONE);
            }
        });

    }

}








