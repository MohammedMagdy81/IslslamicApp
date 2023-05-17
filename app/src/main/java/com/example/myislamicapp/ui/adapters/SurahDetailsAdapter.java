package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.translate.SurahDetail;

import java.util.List;

public class SurahDetailsAdapter extends RecyclerView.Adapter<SurahDetailsAdapter.SurahDetailsViewHolder> {


    List<SurahDetail> soraDetail;


    public SurahDetailsAdapter(List<SurahDetail> soraDetail) {
        this.soraDetail = soraDetail;
    }

    public void setSoraDetail(List<SurahDetail> soraDetail) {
        this.soraDetail = soraDetail;
    }

    @NonNull
    @Override
    public SurahDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahDetailsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sora_detail, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SurahDetailsViewHolder holder, int position) {
        holder.bind(soraDetail.get(position));

    }

    @Override
    public int getItemCount() {
        return soraDetail.size();
    }

    class SurahDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView ayaNo, ayaText, ayaTranslate;

        public SurahDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ayaNo = itemView.findViewById(R.id.itemAyaNo);
            ayaText = itemView.findViewById(R.id.itemAyaTxt);
            ayaTranslate = itemView.findViewById(R.id.itemAyaTxtTranslate);
        }

        public void bind(SurahDetail surahDetail) {
            ayaNo.setText(String.valueOf(surahDetail.getAya()));
            ayaText.setText(surahDetail.getArabicText());
            ayaTranslate.setText(surahDetail.getTranslation());
        }
    }
}
