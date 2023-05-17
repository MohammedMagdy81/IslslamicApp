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
import com.example.myislamicapp.data.pojo.Aya;
import com.example.myislamicapp.ui.fragments.quransearch.SearchFragmentDirections;

import java.util.ArrayList;

public class QuranSearchAdapter extends RecyclerView.Adapter<QuranSearchAdapter.QuranSearchViewHolder> {

    ArrayList<Aya> ayat;
    private Fragment fragment;


    public QuranSearchAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setAyat(ArrayList<Aya> ayat) {
        this.ayat = ayat;
    }

    @NonNull
    @Override
    public QuranSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuranSearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_search_aya, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull QuranSearchViewHolder holder, int position) {
        holder.bind(ayat.get(position));
    }

    @Override
    public int getItemCount() {
        return ayat == null ? 0 : ayat.size();
    }

    public class QuranSearchViewHolder extends RecyclerView.ViewHolder {

        TextView soraName, ayaText, ayaNo, jozzNo;

        public QuranSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            soraName = itemView.findViewById(R.id.soraname);
            ayaText = itemView.findViewById(R.id.ayaText);
            ayaNo = itemView.findViewById(R.id.ayaNo);
            jozzNo = itemView.findViewById(R.id.jozzNum);
        }

        public void bind(@NonNull Aya aya) {
            soraName.setText(" سورة : " + aya.getSora_name_ar());
            ayaNo.setText("رقم الاية : " + aya.getAya_no());
            jozzNo.setText(" جزء : " + aya.getJozz());
            ayaText.setText(aya.getAya_text_emlaey());
            itemView.setOnClickListener(v -> {
              NavHostFragment.findNavController(fragment).navigate(
                      SearchFragmentDirections.actionSearchFragmentToQuranContainerFragment(aya.getPage())
              );
            });
        }
    }
}
