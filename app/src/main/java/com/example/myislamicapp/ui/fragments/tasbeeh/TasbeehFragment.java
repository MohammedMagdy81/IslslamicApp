package com.example.myislamicapp.ui.fragments.tasbeeh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.databinding.FragmentTafseerBinding;
import com.example.myislamicapp.databinding.FragmentTasbeehBinding;
import com.example.myislamicapp.ui.adapters.TasbeehListAdapter;

import java.util.ArrayList;

public class TasbeehFragment extends Fragment {

    private FragmentTasbeehBinding binding;
    private TasbeehViewModel viewModel;
    private TasbeehListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TasbeehViewModel.class);
        adapter = new TasbeehListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTasbeehBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        binding.rvTasbeehList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTasbeehList.setAdapter(adapter);
        binding.rvTasbeehList.setHasFixedSize(true);

        adapter.setAllTasbeehList((ArrayList<String>) viewModel.allTasbeeh(),this);



    }
}