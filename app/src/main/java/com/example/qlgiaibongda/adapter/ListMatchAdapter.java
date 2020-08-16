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
import com.example.qlgiaibongda.model.Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListMatchAdapter extends RecyclerView.Adapter<ListMatchAdapter.MatchViewHolder> {
    public ArrayList<Match> listMatch;
    public ArrayList<Match> listResult;
    private Context context;
    private onItemClickListener mOnItemClickListener;
    public ListMatchAdapter(ArrayList<Match> listMatch, Context context, onItemClickListener mOnItemClickListener) {
        this.listMatch = listMatch;
        this.listResult = new ArrayList<>(listMatch);
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_match_item, parent, false);
        return new MatchViewHolder(itemView, mOnItemClickListener);
    }

    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = listResult.get(position);
        holder.imvLogoHome.setImageResource(R.drawable.manutd);
        holder.imvLogoGuest.setImageResource(R.drawable.manutd);
        holder.tvNameHome.setText(match.getHomeTeam());
        holder.tvNameGuest.setText(match.getGuestTeam());
        if (match.getHomeGoal() != null)
            holder.tvGoalHome.setText(match.getHomeGoal().toString());
        if (match.getGuestGoal() != null)
            holder.tvGoalGuest.setText(match.getGuestGoal().toString());
        if (match.getStateMatch() == 0)
            holder.tvStateMatch.setText("Chưa diễn ra");
        else if (match.getStateMatch() == 1)
            holder.tvStateMatch.setText("Đang diễn ra");
        else if (match.getStateMatch() == 2)
            holder.tvStateMatch.setText("KT");

        Date date = match.getDateStart();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        holder.tvTimeMatch.setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imvLogoHome;
        public ImageView imvLogoGuest;
        public TextView tvNameHome;
        public TextView tvNameGuest;
        public TextView tvGoalHome;
        public TextView tvGoalGuest;
        public TextView tvStateMatch;
        public TextView tvTimeMatch;
        public onItemClickListener itemClickListener;

        public MatchViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imvLogoHome = (ImageView) itemView.findViewById(R.id.logoHomeClub);
            imvLogoGuest = (ImageView) itemView.findViewById(R.id.logoGuestClub);
            tvNameHome = (TextView) itemView.findViewById(R.id.nameHomeClub);
            tvNameGuest = (TextView) itemView.findViewById(R.id.nameGuestClub);
            tvGoalHome = (TextView) itemView.findViewById(R.id.goalsHomeClub);
            tvGoalGuest = (TextView) itemView.findViewById(R.id.goalsGuestClub);
            tvStateMatch = (TextView) itemView.findViewById(R.id.stateOfMatch);
            tvTimeMatch = (TextView) itemView.findViewById(R.id.timeOfMatch);
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
