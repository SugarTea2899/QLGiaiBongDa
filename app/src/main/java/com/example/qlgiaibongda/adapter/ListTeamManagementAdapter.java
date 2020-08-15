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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListTeamManagementAdapter extends RecyclerView.Adapter<ListTeamManagementAdapter.TeamViewHolder> {
    public ArrayList<Team> listTeam;
    private Context context;
    private onItemClickListener mOnItemClickListener;

    public ListTeamManagementAdapter(ArrayList<Team> listTeam, Context context, onItemClickListener mOnItemClickListener) {
        this.listTeam = listTeam;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_management_row, parent, false);
        return new TeamViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = listTeam.get(position);
        holder.imgTeamLogo.setImageResource(R.drawable.manutd);
        holder.tvTeamName.setText("Manchester City");
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class TeamViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgTeamLogo;
        public TextView tvTeamName;
        public onItemClickListener itemClickListener;


        public TeamViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imgTeamLogo = (ImageView) itemView.findViewById(R.id.teamManageRowLogoImageView);
            tvTeamName = (TextView) itemView.findViewById(R.id.teamManageRowNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }
}
