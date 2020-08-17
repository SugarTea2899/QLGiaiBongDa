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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlayerStatisticAdapter extends RecyclerView.Adapter<ListPlayerStatisticAdapter.PlayerViewHolder> {
    public ArrayList<Player> listPlayer;
    private Context context;
    public static int count = 1;
    public ListPlayerStatisticAdapter(ArrayList<Player> listPlayer, Context context) {
        this.listPlayer = listPlayer;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_statistic_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = listPlayer.get(position);
        holder.tvStt.setText(String.valueOf(position + 1));
        holder.tvNamePlayer.setText(player.getName());
        if (player.getTeamInfo().getLogo() == null || player.getTeamInfo().getLogo().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_image)
                    .into(holder.imvLogoClub);
        }
        else {
            Picasso.get()
                    .load(player.getTeamInfo().getLogo())
                    .error(R.drawable.no_image)
                    .into(holder.imvLogoClub);
        }
        holder.tvNameClub.setText(player.getTeamInfo().getName());
        holder.tvGoals.setText(player.getTotalGoal());
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{
        public TextView tvStt;
        public TextView tvNamePlayer;
        public ImageView imvLogoClub;
        public TextView tvNameClub;
        public TextView tvGoals;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = (TextView) itemView.findViewById(R.id.numberPlayerStatistic);
            tvNamePlayer = (TextView) itemView.findViewById(R.id.namePlayerStatistic);
            imvLogoClub = (ImageView) itemView.findViewById(R.id.imageClubStatistic);
            tvNameClub = (TextView) itemView.findViewById(R.id.nameClubStatistic);
            tvGoals = (TextView) itemView.findViewById(R.id.numberGoalsStatistic);
        }
    }
}
