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

import com.example.myislamicapp.R;
import com.example.myislamicapp.databinding.FragmentFortyAhadeethDetailsBinding;
import com.example.myislamicapp.ui.adapters.HadeethDetailsAdapter;

public class FortyAhadeethDetailsFragment extends Fragment {

    private FragmentFortyAhadeethDetailsBinding binding;
    private FortyAhadeethDetailsFragmentArgs args;
    private AhadeethViewModel viewModel;
    private HadeethDetailsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = FortyAhadeethDetailsFragmentArgs.fromBundle(requireArguments());
        adapter = new HadeethDetailsAdapter();
        viewModel = new ViewModelProvider(this).get(AhadeethViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFortyAhadeethDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        binding.rvHadeeth.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvHadeeth.setHasFixedSize(true);
        binding.rvHadeeth.setAdapter(adapter);
        binding.hadeethName.setText(args.getHadeethName());
        adapter.setAhadeeth(viewModel.getAhadeethByIndex(args.getIndex()));

    }
}








