package com.example.myislamicapp.ui.fragments.quranContainer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.databinding.FragmentQuranContainerBinding;
import com.example.myislamicapp.ui.adapters.QuranPagesAdapter;

public class QuranContainerFragment extends Fragment {

    private FragmentQuranContainerBinding binding;
    private QuranContainerFragmentArgs args;
    private FragmentStateAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = QuranContainerFragmentArgs.fromBundle(requireArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuranContainerBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new QuranPagesAdapter(getActivity());
        binding.quranViewPager.setAdapter(adapter);
        binding.quranViewPager.setCurrentItem(604-args.getStartPage(),false);

    }
}
