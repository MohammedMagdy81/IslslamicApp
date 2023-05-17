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
import com.example.myislamicapp.data.pojo.Jozz;
import com.example.myislamicapp.data.pojo.Sora;
import com.example.myislamicapp.ui.fragments.fehres.FehresFragmentDirections;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SoraListAdapter extends RecyclerView.Adapter<SoraListAdapter.SoraListViewHolder> {

    List<?> index;
    Fragment fragment;


    public SoraListAdapter(List<?> index, Fragment fragment) {
        this.index = index;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SoraListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoraListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sora_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SoraListViewHolder holder, int position) {
        if (index.get(position) instanceof Sora) {
            holder.bind((Sora) index.get(position));
        } else if (index.get(position) instanceof Jozz) {
            holder.bind((Jozz) index.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return index.size();
    }

    class SoraListViewHolder extends RecyclerView.ViewHolder {

        TextView soraName, soraDetails;

        public SoraListViewHolder(@NonNull View itemView) {
            super(itemView);
            soraName = itemView.findViewById(R.id.soraName);
            soraDetails = itemView.findViewById(R.id.soraDetails);
        }

        public void bind(Sora sora) {
            String soraNum = Integer.toString(sora.getSoraNumber());
            soraName.setText(soraNum + " - " + sora.getArabicName());
            String from = " من الصفحة ";
            String to = " الي الصفحة ";
            int startPage = sora.getStartPage();
            int endPage = sora.getEndPage();
            soraDetails.setText(from + startPage + to + endPage);

            itemView.setOnClickListener(view -> {
                FehresFragmentDirections.ActionFehresFragmentToQuranContainerFragment action =
                        FehresFragmentDirections.actionFehresFragmentToQuranContainerFragment(sora.getStartPage());
                NavHostFragment.findNavController(fragment).
                        navigate(action);
            });
        }

        public void bind(Jozz jozz) {
            String from = " من الصفحة ";
            String to = " الي الصفحة ";
            int startPage = jozz.getStartPage();
            int endPage = jozz.getEndPage();
            soraDetails.setText(from + startPage + to + endPage);


            NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));
            soraName.setText("الجزء رقم : " + (nf.format(jozz.getJozzNumber())));
            soraDetails.setText(from + startPage + to + endPage);

            itemView.setOnClickListener(v -> {
                FehresFragmentDirections.ActionFehresFragmentToQuranContainerFragment action = FehresFragmentDirections.actionFehresFragmentToQuranContainerFragment(jozz.getStartPage());
                NavHostFragment.findNavController(fragment).navigate(action);
            });

        }
    }
}




