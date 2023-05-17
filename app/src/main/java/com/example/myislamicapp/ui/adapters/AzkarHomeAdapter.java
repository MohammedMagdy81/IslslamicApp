package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.azkar.ZekrType;
import com.example.myislamicapp.ui.fragments.azkar.azkarHome.AzkarHomeFragmentDirections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class AzkarHomeAdapter extends RecyclerView.Adapter<AzkarHomeAdapter.AzkarListViewHolder> {

    ArrayList<ZekrType> azkarType;
    Fragment fragment;

    public AzkarHomeAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setAzkarType(HashSet<ZekrType> azkarType) {
        this.azkarType = new ArrayList<>(azkarType);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AzkarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AzkarListViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_zekr, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarListViewHolder holder, int position) {
        holder.bind(azkarType.get(position));
    }

    @Override
    public int getItemCount() {
        return azkarType.size();
    }

    class AzkarListViewHolder extends RecyclerView.ViewHolder {

        TextView tvZekrType;
        ImageView imageZekr;

        public AzkarListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvZekrType = itemView.findViewById(R.id.zekrType);
            imageZekr = itemView.findViewById(R.id.imageZekr);


        }

        public void bind(ZekrType zekrType) {
            tvZekrType.setText(zekrType.getZekrName());
            imageZekr.setImageResource(R.drawable.praying);
            itemView.setOnClickListener(view -> {
                NavHostFragment.findNavController(fragment)
                        .navigate(AzkarHomeFragmentDirections.actionAzkarFragmentToAzkarDetailFragment(zekrType.getZekrName()));
            });

        }
    }

}














