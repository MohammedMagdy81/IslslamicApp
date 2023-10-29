package com.example.myislamicapp.ui.fragments.ahadeeth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.databinding.FragmentFortyAhadeethBinding;
import com.example.myislamicapp.ui.adapters.AhadeethListAdapter;

import java.util.ArrayList;

public class FortyAhadeethFragment extends Fragment {

    private FragmentFortyAhadeethBinding binding;
    private AhadeethListAdapter adapter;
    private AhadeethViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AhadeethListAdapter(new ArrayList<>(), this);
        viewModel = new ViewModelProvider(this).get(AhadeethViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFortyAhadeethBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAhadeethRecyclerView();
    }

    private void setupAhadeethRecyclerView() {
        binding.rvAhadeethList.setAdapter(adapter);
        binding.rvAhadeethList.setHasFixedSize(true);
        binding.rvAhadeethList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setAhadeethLists(viewModel.getAhadeethList());

    }
}