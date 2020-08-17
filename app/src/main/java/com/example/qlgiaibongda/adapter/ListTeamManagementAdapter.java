package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.ClubDetail;
import com.example.qlgiaibongda.activity.EditTeam;
import com.example.qlgiaibongda.activity.TeamManagement;
import com.example.qlgiaibongda.model.Team;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListTeamManagementAdapter extends RecyclerView.Adapter<ListTeamManagementAdapter.TeamViewHolder>  {
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
        if (team.getLogo() == null || team.getLogo().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_image)
                    .into(holder.imgTeamLogo);
        }
        else {
            Picasso.get()
                    .load(team.getLogo())
                    .error(R.drawable.no_image)
                    .into(holder.imgTeamLogo);
        }
        holder.imgTeamLogo.setImageResource(R.drawable.manutd);
        holder.tvTeamName.setText(team.getName());

        holder.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {

                Intent intent = new Intent(context, ClubDetail.class);
                intent.putExtra("teamId", listTeam.get(i).getId());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public void filterList(ArrayList<Team> filteredList) {
        listTeam = filteredList;
        notifyDataSetChanged();
    }


    public class TeamViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgTeamLogo;
        public TextView tvTeamName;
        public onItemClickListener itemClickListener;


        public void setmOnItemClickListener (onItemClickListener _onItemClickListener){
            this.itemClickListener = _onItemClickListener;
        }

        public TeamViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imgTeamLogo = (ImageView) itemView.findViewById(R.id.teamManageRowLogoImageView);
            tvTeamName = (TextView) itemView.findViewById(R.id.teamManageRowNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //itemClickListener.onItemClick(getAdapterPosition());
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, int i);
    }
}
