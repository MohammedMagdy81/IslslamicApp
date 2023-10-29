package com.example.myislamicapp.ui.fragments.azkar.azkarHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.data.pojo.azkar.ZekrType;
import com.example.myislamicapp.databinding.FragmentAzkarBinding;
import com.example.myislamicapp.ui.adapters.AzkarHomeAdapter;

import java.util.ArrayList;

public class AzkarHomeFragment extends Fragment {

    private FragmentAzkarBinding binding;
    private AzkarHomeAdapter adapter;
    private AzkarHomeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AzkarHomeViewModel.class);
        adapter = new AzkarHomeAdapter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAzkarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    private void setRecyclerView() {
        binding.azkarListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.azkarListRv.setHasFixedSize(true);
        binding.azkarListRv.setAdapter(adapter);
        adapter.setAzkarType(viewModel.getAzkarTypes());

    }
}