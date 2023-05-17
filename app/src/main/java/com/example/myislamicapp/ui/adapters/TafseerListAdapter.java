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
import com.example.myislamicapp.data.pojo.tafseer.TafseerResponseItem;
import com.example.myislamicapp.ui.fragments.tafseer.TafseerFragmentDirections;

import java.util.ArrayList;

public class TafseerListAdapter extends RecyclerView.Adapter<TafseerListAdapter.TafseerViewHolder> {

    ArrayList<TafseerResponseItem> responseItems;
    Fragment fragment;

    public TafseerListAdapter(ArrayList<TafseerResponseItem> responseItems, Fragment fragment) {
        this.responseItems = responseItems;
        this.fragment = fragment;
    }

    public void setResponseItems(ArrayList<TafseerResponseItem> responseItems) {
        this.responseItems = responseItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TafseerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TafseerViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_tafseer, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull TafseerViewHolder holder, int position) {
        holder.bind(responseItems.get(position));
        holder.itemView.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(
                            TafseerFragmentDirections.actionTafseerFragmentToTafseerAyahFragment(responseItems.get(position))
                    );
        });
    }

    @Override
    public int getItemCount() {
        return responseItems == null ? 0 : responseItems.size();
    }

    class TafseerViewHolder extends RecyclerView.ViewHolder {

        TextView tvTafseerName, tvTafseerBookName, tvAuthor;

        public TafseerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTafseerName = itemView.findViewById(R.id.tafseerName);
            tvTafseerBookName = itemView.findViewById(R.id.tafseerBookName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
        }

        public void bind(TafseerResponseItem tafseerResponseItem) {
            tvTafseerName.setText(tafseerResponseItem.getName());
            tvTafseerBookName.setText(tafseerResponseItem.getBookName());
            tvAuthor.setText(tafseerResponseItem.getAuthor());
        }
    }
}
