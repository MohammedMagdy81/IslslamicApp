package com.example.myislamicapp.ui.fragments.quranOnline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myislamicapp.data.pojo.quranResponse.Surah;
import com.example.myislamicapp.data.utils.Network;
import com.example.myislamicapp.databinding.FragmentQuranOnlineBinding;
import com.example.myislamicapp.ui.adapters.SurahOnlineAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuranOnlineFragment extends Fragment {

    private FragmentQuranOnlineBinding binding;
    private QuranOnlineViewModel viewModel;
    private SurahOnlineAdapter adapter;
    private List<Surah> surahs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(QuranOnlineViewModel.class);
        surahs = new ArrayList<>();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuranOnlineBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Network.isInternetConnected(requireContext())){
            viewModel.getSurah();
        }else {
            showNoInternetImage();
        }
        binding.swiperefresh.setOnRefreshListener(() -> {

        });
        binding.surahsRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.surahsRV.setHasFixedSize(true);
        viewModel.data.observe(getViewLifecycleOwner(), surahResponse -> {
            for (int i = 0; i < surahResponse.getData().size(); i++) {
                surahs.add(new Surah(
                        surahResponse.getData().get(i).getNumber(),
                        surahResponse.getData().get(i).getEnglishName(),
                        surahResponse.getData().get(i).getNumberOfAyahs(),
                        surahResponse.getData().get(i).getRevelationType(),
                        surahResponse.getData().get(i).getName(),
                        surahResponse.getData().get(i).getEnglishNameTranslation()));
            }
            if (surahs.size() != 0) {
                adapter = new SurahOnlineAdapter((ArrayList<Surah>) surahs, this);
                binding.surahsRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


        });
        viewModel.messageError.observe(getViewLifecycleOwner(), s -> {
            Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
        });
        viewModel.showProgress.observe(getViewLifecycleOwner(), show -> {
             if (show){
                 binding.progressBar.setVisibility(View.VISIBLE);
             }else {
                 binding.progressBar.setVisibility(View.GONE);
             }
        });
    }

    private void showNoInternetImage() {
        binding.animationView.setVisibility(View.VISIBLE);
        binding.textNoInternet.setVisibility(View.VISIBLE);
    }

}









