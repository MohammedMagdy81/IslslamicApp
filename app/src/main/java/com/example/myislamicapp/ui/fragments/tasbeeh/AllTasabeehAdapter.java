package com.example.myislamicapp.ui.fragments.tasbeeh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;

import java.util.ArrayList;

public class AllTasabeehAdapter extends RecyclerView.Adapter<AllTasabeehAdapter.AllTasabeehViewHolder> {

    ArrayList<Tasbeeh> allTasabeeh;

    public void setAllTasabeeh(ArrayList<Tasbeeh> allTasabeeh) {
        this.allTasabeeh = allTasabeeh;
    }

    @NonNull
    @Override
    public AllTasabeehViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllTasabeehViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_all_tasabeeh, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AllTasabeehViewHolder holder, int position) {
        holder.bind(allTasabeeh.get(position));

    }

    @Override
    public int getItemCount() {
        return allTasabeeh == null ? 0 : allTasabeeh.size();
    }

    class AllTasabeehViewHolder extends RecyclerView.ViewHolder {

        TextView tvTasbeehName, tvTasbeehCount, tvTasbeehDate;

        public AllTasabeehViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTasbeehName = itemView.findViewById(R.id.itemTasbeehName);
            tvTasbeehCount = itemView.findViewById(R.id.itemTasbeehCount);
            tvTasbeehDate = itemView.findViewById(R.id.itemTasbeehDate);
        }

        public void bind(Tasbeeh tasbeeh) {
            tvTasbeehName.setText(tasbeeh.getName());
            if (tasbeeh.getCount() == 1) {
                tvTasbeehCount.setText("مرة واحدة");
            } else if (tasbeeh.getCount() == 2) {
                tvTasbeehCount.setText("مرتان");
            } else if (tasbeeh.getCount() > 2 && tasbeeh.getCount() <= 10) {
                tvTasbeehCount.setText(tasbeeh.getCount() + " مرات ");
            } else {
                tvTasbeehCount.setText( tasbeeh.getCount() + " مرة ");
            }
            tvTasbeehDate.setText(tasbeeh.getTimeStamp());
        }
    }
}
