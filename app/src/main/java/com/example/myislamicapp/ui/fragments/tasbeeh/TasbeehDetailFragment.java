package com.example.myislamicapp.ui.fragments.tasbeeh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;
import com.example.myislamicapp.databinding.FragmentTasbeehDetailBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class TasbeehDetailFragment extends Fragment {

    private FragmentTasbeehDetailBinding binding;
    private TasbeehDetailFragmentArgs args;
    private TasbeehViewModel viewModel;
    int currnetCounter = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = TasbeehDetailFragmentArgs.fromBundle(requireArguments());
        viewModel = new ViewModelProvider(this).get(TasbeehViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTasbeehDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tasbeehName.setText(args.getTasbeehName());
        binding.tabCounter.setOnClickListener(view1 -> {
            binding.textCounter.setText(String.valueOf(++currnetCounter));
        });
        binding.saveTasbeeh.setOnClickListener(view1 -> {
            Tasbeeh tasbeeh = new Tasbeeh(args.getTasbeehName(), Integer.parseInt(binding.textCounter.getText().toString()),
                    getCurrentTime());
            viewModel.insertTasbeeh(tasbeeh);
            Toasty.success(requireContext(), "تم حفظ التسبيح بنجاح ", Toast.LENGTH_LONG, true).show();
            currnetCounter =0 ;
            binding.textCounter.setText("0");

        });
        binding.seeAllTasbeeh.setOnClickListener(view12 -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_tasbeehDetailFragment_to_allTasabeehFragment);
        });
    }

    private String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd    HH:mm", Locale.ENGLISH);
        return dateFormat.format(currentTime);
    }
}