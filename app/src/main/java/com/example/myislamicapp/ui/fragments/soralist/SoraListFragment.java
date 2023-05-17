package com.example.myislamicapp.ui.fragments.soralist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myislamicapp.data.utils.QuranFehresTabsUtils;
import com.example.myislamicapp.databinding.FragmentSoraListBinding;
import com.example.myislamicapp.ui.adapters.SoraListAdapter;

public class SoraListFragment extends Fragment {

    private FragmentSoraListBinding binding;

    private SoraListAdapter adapter;
    private SoraListViewModel viewModel;

    QuranFehresTabsUtils.QuranTabs currentTab;

    public SoraListFragment(QuranFehresTabsUtils.QuranTabs currentTab) {
        this.currentTab = currentTab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SoraListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSoraListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSoraListAdapter();
    }

    private void setUpSoraListAdapter() {
        binding.surasRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SoraListAdapter(viewModel.provideTabsList(currentTab), this);
        binding.surasRV.setAdapter(adapter);


    }
}









