package com.example.myislamicapp.ui.fragments.quranPage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myislamicapp.databinding.FragmentPageQuranBinding;

public class QuranPageFragment extends Fragment {

    private FragmentPageQuranBinding binding;
    private QuranViewModel viewModel;

    private final int pageNumber;

    public QuranPageFragment(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPageQuranBinding.inflate(inflater, container, false);
        viewModel = new QuranViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Drawable quranImage = viewModel.getQuranImageByPageNumber(getContext(), pageNumber);
        binding.imageQuran.setImageDrawable(quranImage);
    }
}
