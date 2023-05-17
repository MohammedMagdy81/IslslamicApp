package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.prayer.PrayerTiming;

import java.util.ArrayList;

public class PrayerTimeListAdapter extends RecyclerView.Adapter<PrayerTimeListAdapter.PrayerTimeViewHolder> {

    private ArrayList<PrayerTiming> prayerTiming;

    public void setPrayerTiming(ArrayList<PrayerTiming> prayerTiming) {
        this.prayerTiming = prayerTiming;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PrayerTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrayerTimeViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_prayer, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PrayerTimeViewHolder holder, int position) {
        holder.bind(prayerTiming.get(position));
    }

    @Override
    public int getItemCount() {
        return prayerTiming == null ? 0 : prayerTiming.size();
    }

    class PrayerTimeViewHolder extends RecyclerView.ViewHolder {

        TextView prayerEnglishName, prayerArabicName, prayerTime;

        public PrayerTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerEnglishName = itemView.findViewById(R.id.prayerEnglishName);
            prayerArabicName = itemView.findViewById(R.id.prayerArabicName);
            prayerTime = itemView.findViewById(R.id.prayerTime);
        }

        public void bind(PrayerTiming prayerTiming) {
            prayerEnglishName.setText(prayerTiming.getPrayerEnglishName());
            prayerArabicName.setText(prayerTiming.getPrayerArabicName());
            prayerTime.setText(prayerTiming.getPrayerTime());
        }
    }
}
