package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.AddPlayer;
import com.example.qlgiaibongda.activity.PlayerManagement;
import com.example.qlgiaibongda.model.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlayerManagementAdapter extends RecyclerView.Adapter<ListPlayerManagementAdapter.PlayerViewHolder> {

    public ArrayList<Player> listPlayer;
    private Context context;
    private onItemClickListener mOnItemClickListener;

    public ListPlayerManagementAdapter(ArrayList<Player> listPlayer, Context context, onItemClickListener mOnItemClickListener) {
        this.listPlayer = listPlayer;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_management_row, parent, false);
        return new PlayerViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = listPlayer.get(position);
        holder.tvShirtNumber.setText(player.getNumber().toString());
        if (player.getAvatar() == null || player.getAvatar().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_avatar)
                    .into(holder.imgPlayerPhoto);
        }
        else {
            Picasso.get()
                    .load(player.getAvatar())
                    .error(R.drawable.no_avatar)
                    .into(holder.imgPlayerPhoto);
        }
        holder.tvPlayerClub.setText(PlayerManagement.teamIdToTeamNameHashMap.get(player.getTeamId()));
        holder.tvPlayerName.setText(player.getName());
        holder.tvPlayerFreeAgent.setText("Cầu thủ tự do");
        holder.imgPlayerClub.setImageResource(R.drawable.manutd);
        if (player.getTeamId() == null)
        {
           // holder.tvPlayerFreeAgent.setText("Cầu thủ tự do");
            holder.tvPlayerFreeAgent.setVisibility(View.VISIBLE);
            holder.imgPlayerClub.setVisibility(View.INVISIBLE);
            holder.tvPlayerClub.setVisibility(View.INVISIBLE);
        }
        else
        {
           // holder.tvPlayerFreeAgent.setText("Cầu thủ tự do");
            holder.tvPlayerFreeAgent.setVisibility(View.INVISIBLE);
            holder.imgPlayerClub.setVisibility(View.VISIBLE);
            holder.tvPlayerClub.setVisibility(View.VISIBLE);
        }


        holder.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {
                Toast.makeText(context,player.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddPlayer.class);
//               
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
    }


    public void filterList(ArrayList<Player> filteredList) {
        listPlayer = filteredList;
        notifyDataSetChanged();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvShirtNumber;
        public ImageView imgPlayerPhoto;
        public TextView tvPlayerName;
        public ImageView imgPlayerClub;
        public TextView tvPlayerClub;
        public TextView tvPlayerFreeAgent;
        public ImageButton imgButtonMoreInfo;
        public onItemClickListener itemClickListener;

        public void setmOnItemClickListener (onItemClickListener _onItemClickListener){
            this.itemClickListener = _onItemClickListener;
        }

        public PlayerViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            tvShirtNumber = (TextView) itemView.findViewById(R.id.playerManageRowShirtNumberTextView);
            imgPlayerPhoto = (ImageView) itemView.findViewById(R.id.playerManageRowPhotoImageView);
            tvPlayerName = (TextView) itemView.findViewById(R.id.playerManageRowNameTextView);
            imgPlayerClub = (ImageView) itemView.findViewById(R.id.playerManageRowClubImageView);
            tvPlayerClub = (TextView) itemView.findViewById(R.id.playerManageRowClubTextView);
            tvPlayerFreeAgent = (TextView) itemView.findViewById(R.id.playerManageRowFreeAgentTextView);
            imgButtonMoreInfo = (ImageButton) itemView.findViewById(R.id.playerManageRowMoreInfoImageButton);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, int i);
    }

}
