package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.ahadeeth.Hadith;

import java.util.ArrayList;

public class HadeethDetailsAdapter extends RecyclerView.Adapter<HadeethDetailsAdapter.HadeethDetailsViewHolder> {

    private ArrayList<Hadith> ahadeeth;

    public void setAhadeeth(ArrayList<Hadith> ahadeeth) {
        this.ahadeeth = ahadeeth;
    }

    @NonNull
    @Override
    public HadeethDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HadeethDetailsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hadeeth_detail, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HadeethDetailsViewHolder holder, int position) {
        holder.bind(ahadeeth.get(position));
    }

    @Override
    public int getItemCount() {
        return ahadeeth.size();
    }

    class HadeethDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView tvHadeeth;

        public HadeethDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHadeeth = itemView.findViewById(R.id.hadeeth);
        }

        public void bind(Hadith hadith) {
            tvHadeeth.setText(hadith.getHadeethName());
        }
    }

}
