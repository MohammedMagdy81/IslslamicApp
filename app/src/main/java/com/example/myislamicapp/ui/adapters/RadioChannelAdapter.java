package com.example.myislamicapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.radio.RadioChannel;

import java.util.ArrayList;

public class RadioChannelAdapter extends RecyclerView.Adapter<RadioChannelAdapter.RadioChannelViewHolder> {

    ArrayList<RadioChannel> radioChannels;

    public interface OnRadioClick {
        void onRadioItemClick(int position, String url);
    }

    public OnRadioClick onPlayRadioClick;
    public OnRadioClick onStopRadioClick;

    public void setRadioChannels(ArrayList<RadioChannel> radioChannels) {
        this.radioChannels = radioChannels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RadioChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadioChannelViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_radio, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RadioChannelViewHolder holder, int position) {
        holder.bind(radioChannels.get(position));
        if (onPlayRadioClick != null) {
            holder.imagePlay.setOnClickListener(view -> {
                onPlayRadioClick.onRadioItemClick(position, radioChannels.get(position).getRadio_url());
            });
        }
        if (onStopRadioClick != null) {
            holder.imageStop.setOnClickListener(view -> {
                onStopRadioClick.onRadioItemClick(position, radioChannels.get(position).getRadio_url());
            });
        }
    }

    @Override
    public int getItemCount() {
        return radioChannels == null ? 0 : radioChannels.size();
    }

    class RadioChannelViewHolder extends RecyclerView.ViewHolder {
        TextView channelName;
        ImageView imagePlay, imageStop;

        public RadioChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.channelName);
            imagePlay = itemView.findViewById(R.id.imagePlayRadio);
            imageStop = itemView.findViewById(R.id.imageStopRadio);
        }

        public void bind(RadioChannel radioChannel) {
            channelName.setText(radioChannel.getName());
        }
    }
}
