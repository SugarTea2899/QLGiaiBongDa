package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Player;

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
        holder.tvShirtNumber.setText(String.format("%d", position * 3));
        holder.imgPlayerPhoto.setImageResource(R.drawable.old_trafford);
        holder.tvPlayerClub.setText("Manchester United");
        holder.tvPlayerName.setText("Edwin Van der sar");
        holder.tvPlayerFreeAgent.setText("Cầu thủ tự do");
        holder.imgPlayerClub.setImageResource(R.drawable.manutd);
        if (position % 2 == 0)
        {
            holder.tvPlayerFreeAgent.setVisibility(View.VISIBLE);
            holder.imgPlayerClub.setVisibility(View.INVISIBLE);
            holder.tvPlayerClub.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.tvPlayerFreeAgent.setVisibility(View.INVISIBLE);
            holder.imgPlayerClub.setVisibility(View.VISIBLE);
            holder.tvPlayerClub.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
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
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }

}
