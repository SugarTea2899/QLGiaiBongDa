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
import com.example.qlgiaibongda.constant.Constant;
import com.example.qlgiaibongda.model.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SelectPlayerAdapter extends RecyclerView.Adapter<SelectPlayerAdapter.SelectPlayer>{

    private ArrayList<Player> listPlayer;
    private Context context;

    public SelectPlayerAdapter(ArrayList listPlayer, Context context){
        this.listPlayer = listPlayer;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_player_item, parent, false);
        return new SelectPlayer(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPlayer holder, int position) {
        Player player = listPlayer.get(position);
        holder.tvPlayerNumber.setText( String.valueOf(player.getNumber()));
        holder.tvPlayerName.setText(player.getName());

        switch (player.getType()){
            case Constant.DEFENDER:
                holder.tvPlayerType.setText("Hậu vệ");
                break;
            case Constant.FORWARD:
                holder.tvPlayerType.setText("Tiền đạo");
                break;
            case Constant.MIDFIELDER:
                holder.tvPlayerType.setText("Tiền vệ");
                break;
            case Constant.GOAL_KEEPER:
                holder.tvPlayerType.setText("Thủ môn");
                break;
                default:
                    holder.tvPlayerType.setText("Không tìm thấy");
        }

        Picasso.get()
                .load(player.getAvatar())
                .error(R.drawable.no_logo)
                .resize(30, 45)
                .into(holder.imvPlayerAvatar);
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
    }


    public class SelectPlayer extends RecyclerView.ViewHolder{
        TextView tvPlayerNumber;
        TextView tvPlayerName;
        TextView tvPlayerType;
        ImageView imvPlayerAvatar;
        public SelectPlayer(@NonNull View itemView) {
            super(itemView);
            tvPlayerName = (TextView)itemView.findViewById(R.id.tvPlayerName);
            tvPlayerNumber = (TextView) itemView.findViewById(R.id.tvPlayerNumber);
            tvPlayerType = (TextView) itemView.findViewById(R.id.tvPlayerType);
            imvPlayerAvatar = (ImageView) itemView.findViewById(R.id.imvPlayerAvatar);
        }
    }

}
