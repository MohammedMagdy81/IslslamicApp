package com.example.myislamicapp.ui.fragments.tafseer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.myislamicapp.data.pojo.tafseer.SuraTafseerResponse;
import com.example.myislamicapp.databinding.FragmentTafseerAyahBinding;

public class TafseerAyahFragment extends Fragment {

    private FragmentTafseerAyahBinding binding;
    private TafseerViewModel viewModel;
    private TafseerAyahFragmentArgs args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = TafseerAyahFragmentArgs.fromBundle(requireArguments());
        viewModel = new ViewModelProvider(this).get(TafseerViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTafseerAyahBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeToTafseerValue();
        binding.numOfAyah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!binding.numOfSura.getText().toString().isEmpty() &&
                        !binding.numOfAyah.getText().toString().isEmpty()) {

                    viewModel.getTafseerAyah(args.getTafseerResponse().getId(),
                            Integer.parseInt(binding.numOfSura.getText().toString()),
                            Integer.parseInt(charSequence.toString())

                    );
                    viewModel.getSpecificAyah(Integer.parseInt(binding.numOfSura.getText().toString()),
                            Integer.parseInt(charSequence.toString()));

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void observeToTafseerValue() {
        viewModel.ayahTafseer.observe(getViewLifecycleOwner(), tafseer -> {
            if (tafseer != null) {
                binding.ayahTafseer.setText(tafseer.getText());
            }
        });
        viewModel.isTextShow.observe(getViewLifecycleOwner(), isTextShow -> {
            if (isTextShow) {
                binding.ayahText.setVisibility(View.VISIBLE);
            } else {
                binding.ayahText.setVisibility(View.INVISIBLE);
            }
        });
        viewModel.ayah.observe(getViewLifecycleOwner(), suraTafseerResponse -> {
            if (suraTafseerResponse != null) {
                binding.ayahImage.setText(suraTafseerResponse.getText());
                binding.suraName.setText(" سورة "+ suraTafseerResponse.getSuraName());
            }
        });
        viewModel.showProgress.observe(getViewLifecycleOwner(), show -> {
            if (show)
                binding.progress.setVisibility(View.VISIBLE);
            else
                binding.progress.setVisibility(View.GONE);
        });
    }


}















