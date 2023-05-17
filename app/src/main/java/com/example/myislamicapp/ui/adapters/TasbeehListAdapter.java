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
import com.example.myislamicapp.ui.fragments.tasbeeh.TasbeehFragmentDirections;

import java.util.ArrayList;

public class TasbeehListAdapter extends RecyclerView.Adapter<TasbeehListAdapter.TasbeehListViewHolder> {

    ArrayList<String> allTasbeehList;

    Fragment fragment;

    public void setAllTasbeehList(ArrayList<String> allTasbeehList,Fragment fragment) {
        this.allTasbeehList = allTasbeehList;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public TasbeehListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasbeehListViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_tasbeeh_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TasbeehListViewHolder holder, int position) {
        holder.bind(allTasbeehList.get(position));
        holder.itemView.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(TasbeehFragmentDirections.actionTasbeehFragmentToTasbeehDetailFragment(
                            allTasbeehList.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return allTasbeehList.size();
    }

    class TasbeehListViewHolder extends RecyclerView.ViewHolder {

        TextView tvTasbeehName;

        public TasbeehListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTasbeehName = itemView.findViewById(R.id.tasbeehName);
        }

        public void bind(String tasbeehName) {
            tvTasbeehName.setText(tasbeehName);
        }
    }
}
