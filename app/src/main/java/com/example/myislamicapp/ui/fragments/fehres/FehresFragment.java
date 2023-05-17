package com.example.myislamicapp.ui.fragments.fehres;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.databinding.FragmentFehresBinding;
import com.example.myislamicapp.ui.adapters.QuranFehresPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FehresFragment extends Fragment {


    private FragmentFehresBinding binding;

    QuranFehresPagerAdapter adapter;
    QuranFehresViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFehresBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new QuranFehresPagerAdapter(this);
        binding.fehresPager.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(QuranFehresViewModel.class);
        new TabLayoutMediator(binding.tabsFehres, binding.fehresPager,
                (tab, position) -> tab.setText(viewModel.getTabAt(position))
        ).attach();

        binding.editTextSearch.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fehresFragment_to_searchFragment);
        });
    }
}