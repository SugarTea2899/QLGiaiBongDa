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
import com.example.qlgiaibongda.activity.MatchInfo;
import com.example.qlgiaibongda.model.Match;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListMatchAdapter extends RecyclerView.Adapter<ListMatchAdapter.MatchViewHolder> {
    public ArrayList<Match> listMatch;
    private Context context;
    private onItemClickListener mOnItemClickListener;
    public ListMatchAdapter(ArrayList<Match> listMatch, Context context, onItemClickListener mOnItemClickListener) {
        this.listMatch = listMatch;
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
        Match match = listMatch.get(position);
        if (match.getLogoHome() == null || match.getLogoHome().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_image)
                    .into(holder.imvLogoHome);
        }
        else {
            Picasso.get()
                    .load(match.getLogoHome())
                    .error(R.drawable.no_image)
                    .into(holder.imvLogoHome);
        }
        if (match.getLogoGuest() == null || match.getLogoGuest().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_image)
                    .into(holder.imvLogoGuest);
        }
        else {
            Picasso.get()
                    .load(match.getLogoGuest())
                    .error(R.drawable.no_image)
                    .into(holder.imvLogoGuest);
        }
        holder.tvNameHome.setText(match.getHomeTeam());
        holder.tvNameGuest.setText(match.getGuestTeam());
        if (match.getHomeGoal() != null && match.getStateMatch() != 0)
            holder.tvGoalHome.setText(match.getHomeGoal().toString());
        if (match.getGuestGoal() != null && match.getStateMatch() != 0)
            holder.tvGoalGuest.setText(match.getGuestGoal().toString());
        if (match.getStateMatch() == 0)
            holder.tvStateMatch.setText("Chưa diễn ra");
        else if (match.getStateMatch() == 1)
            holder.tvStateMatch.setText("Đang diễn ra");
        else if (match.getStateMatch() == 2)
           holder.tvStateMatch.setText("KT");

        Date date = match.getDateStart();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd/MM");
        holder.tvTimeMatch.setText(sdf.format(date));

        holder.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {
                Intent intent = new Intent(context, MatchInfo.class);
                intent.putExtra("matchId", listMatch.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMatch.size();
    }

    public void filterList(ArrayList<Match> list) {
        listMatch = list;
        notifyDataSetChanged();
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

        public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
            this.itemClickListener = mOnItemClickListener;
        }

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
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, int i);
    }
}
