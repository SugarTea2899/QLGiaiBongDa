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
import com.example.qlgiaibongda.model.Team;

import java.util.ArrayList;

public class ListRankingAdapter extends RecyclerView.Adapter<ListRankingAdapter.TeamViewHolder> {
    public ArrayList<Team> listTeam;
    private Context context;
    private onItemClickListener mOnItemClickListener;
    public ListRankingAdapter(ArrayList<Team> listTeam, Context context, onItemClickListener mOnItemClickListener) {
        this.listTeam = listTeam;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ranking_item, parent, false);
        return new TeamViewHolder(itemView, mOnItemClickListener);
    }

    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = listTeam.get(position);
        holder.tvRank.setText(String.valueOf(position + 1));
        holder.imvState.setImageResource(R.drawable.ic_arrow_drop_down);
        holder.imvLogoClub.setImageResource(R.drawable.manutd);
        holder.tvNameClub.setText("Manchester United");
        holder.tvMatchCount.setText("30");
        holder.tvCoefficient.setText("50");
        holder.tvPoint.setText("80");
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvRank;
        public ImageView imvState;
        public ImageView imvLogoClub;
        public TextView tvNameClub;
        public TextView tvMatchCount;
        public TextView tvCoefficient;
        public TextView tvPoint;
        public onItemClickListener itemClickListener;

        public TeamViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            tvRank = (TextView) itemView.findViewById(R.id.rank);
            imvState = (ImageView) itemView.findViewById(R.id.icon);
            imvLogoClub = (ImageView) itemView.findViewById(R.id.logoClub);
            tvNameClub = (TextView) itemView.findViewById(R.id.clubName);
            tvMatchCount = (TextView) itemView.findViewById(R.id.matchCount);
            tvCoefficient = (TextView) itemView.findViewById(R.id.coefficient);
            tvPoint = (TextView) itemView.findViewById(R.id.point);
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
