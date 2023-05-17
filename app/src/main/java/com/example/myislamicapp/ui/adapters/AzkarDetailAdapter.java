package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.azkar.Zekr;
import com.example.myislamicapp.data.pojo.azkar.ZekrType;

import java.util.ArrayList;

public class AzkarDetailAdapter extends RecyclerView.Adapter<AzkarDetailAdapter.AzkarDetailViewHolder> {

    ArrayList<Zekr> azkar;

    public void setAzkar(ArrayList<Zekr> azkar) {
        this.azkar = azkar;
    }

    @NonNull
    @Override
    public AzkarDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AzkarDetailViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_zekr_detail, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarDetailViewHolder holder, int position) {
        holder.bind(azkar.get(position));
    }

    @Override
    public int getItemCount() {
        return azkar.size();
    }

    class AzkarDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView tvZekr,tvZekrCount;

        public AzkarDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            tvZekr = itemView.findViewById(R.id.zekr);
            tvZekrCount = itemView.findViewById(R.id.zekrCount);
        }

        public void bind(Zekr zekr) {
            tvZekr.setText(zekr.getZekr());
            if (!zekr.getCount().isBlank()){
                tvZekrCount.setText("تقال "+zekr.getCount()+ " مرات ");
            }
        }
    }
}
