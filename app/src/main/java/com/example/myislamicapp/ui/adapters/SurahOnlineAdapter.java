package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.quranResponse.Surah;
import com.example.myislamicapp.ui.fragments.quranOnline.QuranOnlineFragmentDirections;

import java.util.ArrayList;

public class SurahOnlineAdapter extends RecyclerView.Adapter<SurahOnlineAdapter.SurahOnlineViewHolder> {

    ArrayList<Surah> surahArrayList;
    Fragment fragment;


    public SurahOnlineAdapter(ArrayList<Surah> surahArrayList, Fragment fragment) {
        this.surahArrayList = surahArrayList;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public SurahOnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahOnlineViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_surah, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SurahOnlineViewHolder holder, int position) {
        holder.bind(surahArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return surahArrayList.size();
    }

    class SurahOnlineViewHolder extends RecyclerView.ViewHolder {

        TextView surahNumber, surahArName, surahEnName, surahAyaNo;

        public SurahOnlineViewHolder(@NonNull View itemView) {
            super(itemView);
            surahNumber = itemView.findViewById(R.id.surah_number);
            surahArName = itemView.findViewById(R.id.arabic_name);
            surahEnName = itemView.findViewById(R.id.english_name);
            surahAyaNo = itemView.findViewById(R.id.total_aya);
        }

        public void bind(Surah surah) {
            surahNumber.setText(String.valueOf(surah.getNumber()));
            surahArName.setText(surah.getName());
            surahEnName.setText(surah.getEnglishName());
            surahAyaNo.setText(String.valueOf(surah.getNumberOfAyahs()));
            itemView.setOnClickListener(view -> {
                NavHostFragment.findNavController(fragment).navigate(QuranOnlineFragmentDirections.
                        actionQuranOnlineFragmentToSurahDetailsFragment(surah));
            });
        }
    }
}
