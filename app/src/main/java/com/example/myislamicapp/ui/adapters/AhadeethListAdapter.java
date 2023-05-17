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
import com.example.myislamicapp.data.pojo.ahadeeth.AhadeethList;
import com.example.myislamicapp.ui.fragments.ahadeeth.FortyAhadeethFragmentDirections;

import java.util.ArrayList;

public class AhadeethListAdapter extends RecyclerView.Adapter<AhadeethListAdapter.AhadeethListViewHolder> {

    ArrayList<AhadeethList> ahadeethLists;
    Fragment fragment;

    public AhadeethListAdapter(ArrayList<AhadeethList> ahadeethLists, Fragment fragment) {
        this.ahadeethLists = ahadeethLists;
        this.fragment = fragment;
    }

    public void setAhadeethLists(ArrayList<AhadeethList> ahadeethLists) {
        this.ahadeethLists = ahadeethLists;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AhadeethListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AhadeethListViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hadeeth_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AhadeethListViewHolder holder, int position) {
        holder.bind(ahadeethLists.get(position));
        holder.itemView.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment).navigate(
                    FortyAhadeethFragmentDirections.actionFortyAhadeethFragmentToFortyAhadeethDetailsFragment(position, ahadeethLists.get(position).getName())
            );
        });

    }

    @Override
    public int getItemCount() {
        return ahadeethLists.size();
    }

    class AhadeethListViewHolder extends RecyclerView.ViewHolder {

        TextView textHadeethName;

        public AhadeethListViewHolder(@NonNull View itemView) {
            super(itemView);
            textHadeethName = itemView.findViewById(R.id.textHadethName);
        }

        public void bind(AhadeethList ahadeethList) {
            textHadeethName.setText(ahadeethList.getName());
        }
    }
}
