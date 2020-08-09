package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class ListPlayerStatisticAdapter extends RecyclerView.Adapter<ListPlayerStatisticAdapter.PlayerViewHolder> {
    public ArrayList<Player> listPlayer;
    private Context context;
    private onItemClickListener mOnItemClickListener;
    public ListPlayerStatisticAdapter(ArrayList<Player> listPlayer, Context context, onItemClickListener mOnItemClickListener) {
        this.listPlayer = listPlayer;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_statistic_item, parent, false);
        return new PlayerViewHolder(itemView, mOnItemClickListener);
    }

    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player match = listPlayer.get(position);
        holder.tvStt.setText("1");
        holder.tvNamePlayer.setText("Jamie Vardy");
        holder.imvLogoClub.setImageResource(R.drawable.manutd);
        holder.tvNameClub.setText("Manchester United");
        holder.tvGoals.setText("23");
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvStt;
        public TextView tvNamePlayer;
        public ImageView imvLogoClub;
        public TextView tvNameClub;
        public TextView tvGoals;
        public onItemClickListener itemClickListener;

        public PlayerViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            tvStt = (TextView) itemView.findViewById(R.id.numberPlayerStatistic);
            tvNamePlayer = (TextView) itemView.findViewById(R.id.namePlayerStatistic);
            imvLogoClub = (ImageView) itemView.findViewById(R.id.imageClubStatistic);
            tvNameClub = (TextView) itemView.findViewById(R.id.nameClubStatistic);
            tvGoals = (TextView) itemView.findViewById(R.id.numberGoalsStatistic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }
}
