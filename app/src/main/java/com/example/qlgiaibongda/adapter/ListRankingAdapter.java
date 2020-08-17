package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.ClubDetail;
import com.example.qlgiaibongda.activity.MainActivity;
import com.example.qlgiaibongda.activity.MatchInfo;
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
        holder.tvNameClub.setText(team.getName());
        int win = MainActivity.teamNameToRankHashMap.get(team.getName()).getWin();
        int draw = MainActivity.teamNameToRankHashMap.get(team.getName()).getDraw();
        int loss = MainActivity.teamNameToRankHashMap.get(team.getName()).getLoss();
        int goal = MainActivity.teamNameToRankHashMap.get(team.getName()).getGoal();
        int conceded = MainActivity.teamNameToRankHashMap.get(team.getName()).getConceded();
        Integer matchNum = win + draw + loss;
        Integer coefficient = goal - conceded;
        Integer point = MainActivity.teamNameToRankHashMap.get(team.getName()).getPoint();
        holder.tvMatchCount.setText(matchNum.toString());
        holder.tvCoefficient.setText(coefficient.toString());
        holder.tvPoint.setText(point.toString());

        holder.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {
                Intent intent = new Intent(context, ClubDetail.class);
                // intent.putExtra("playerId", player.getId());
//
                context.startActivity(intent);
            }
        });
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

        public void setmOnItemClickListener (onItemClickListener _onItemClickListener){
            this.itemClickListener = _onItemClickListener;
        }

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
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, int i);
    }
}
