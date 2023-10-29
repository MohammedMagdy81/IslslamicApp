package com.example.myislamicapp.ui.fragments.quransearch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.database.QuranDao;
import com.example.myislamicapp.data.database.QuranDatabase;
import com.example.myislamicapp.data.pojo.Aya;
import com.example.myislamicapp.databinding.FragmentSearchBinding;
import com.example.myislamicapp.ui.adapters.QuranSearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private QuranSearchAdapter adapter;
    private SearchViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new QuranSearchAdapter(this);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.searchRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.searchRV.setAdapter(adapter);

        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<Aya> ayas = viewModel.getSearchAyat(editable.toString());
                adapter.setAyat((ArrayList<Aya>) ayas);
            }
        });
    }
}














