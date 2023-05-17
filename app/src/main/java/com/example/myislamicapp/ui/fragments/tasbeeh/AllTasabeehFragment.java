package com.example.myislamicapp.ui.fragments.tasbeeh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;
import com.example.myislamicapp.databinding.FragmentAllTasabeehBinding;

import java.util.ArrayList;
import java.util.List;


public class AllTasabeehFragment extends Fragment {

    private FragmentAllTasabeehBinding binding;
    private TasbeehViewModel viewModel;
    private AllTasabeehAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TasbeehViewModel.class);
        adapter = new AllTasabeehAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAllTasabeehBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {

        binding.rvAllTasabeeh.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvAllTasabeeh.setHasFixedSize(true);
        binding.rvAllTasabeeh.setAdapter(adapter);
        viewModel.getAllTasbeeh();
        viewModel.allTasbeeh.observe(getViewLifecycleOwner(), tasbeehs -> {
           adapter.setAllTasabeeh((ArrayList<Tasbeeh>) tasbeehs);
        });
    }
}