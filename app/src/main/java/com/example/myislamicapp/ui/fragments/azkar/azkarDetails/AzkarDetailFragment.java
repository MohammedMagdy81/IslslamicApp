package com.example.myislamicapp.ui.fragments.azkar.azkarDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.databinding.FragmentAzkarDetailBinding;
import com.example.myislamicapp.ui.adapters.AzkarDetailAdapter;
import com.example.myislamicapp.ui.fragments.azkar.azkarHome.AzkarHomeViewModel;

public class AzkarDetailFragment extends Fragment {

    private FragmentAzkarDetailBinding binding;
    private AzkarHomeViewModel viewModel;
    private AzkarDetailFragmentArgs args;
    private AzkarDetailAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AzkarHomeViewModel.class);
        adapter = new AzkarDetailAdapter();
        args = AzkarDetailFragmentArgs.fromBundle(requireArguments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAzkarDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    private void setRecyclerView() {
        binding.azkarDetailRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.azkarDetailRv.setHasFixedSize(true);
        binding.azkarDetailRv.setAdapter(adapter);
        adapter.setAzkar(viewModel.getAzkarByType(args.getZekrType()));
    }
}











