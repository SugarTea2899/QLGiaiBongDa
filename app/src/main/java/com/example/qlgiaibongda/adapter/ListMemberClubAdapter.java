package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.PlayerDetail;
import com.example.qlgiaibongda.model.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMemberClubAdapter extends RecyclerView.Adapter<ListMemberClubAdapter.MemberViewHolder> {
    public ArrayList<Player> listMember;
    private Context context;
    public ListMemberClubAdapter(ArrayList<Player> listPlayer, Context context) {
        this.listMember = listPlayer;
        this.context = context;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_member_item, parent, false);
        return new MemberViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ListMemberClubAdapter.MemberViewHolder holder, int position) {
        Player player = listMember.get(position);
        holder.tvNumber.setText(player.getNumber().toString());
        holder.tvNamePlayer.setText(player.getName());
        if (player.getAvatar() == null || player.getAvatar().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_avatar)
                    .into(holder.imvPlayer);
        }
        else {
            Picasso.get()
                    .load(player.getAvatar())
                    .error(R.drawable.no_avatar)
                    .into(holder.imvPlayer);
        }
        holder.tvNationality.setText(player.getNationality());
    }

    @Override
    public int getItemCount() {
        return listMember.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNumber;
        public TextView tvNamePlayer;
        public ImageView imvPlayer;
        public TextView tvNationality;
        public ImageButton imbDetail;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.numberPlayer);
            tvNamePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            imvPlayer = (ImageView) itemView.findViewById(R.id.imageOfPlayer);
            tvNationality = (TextView) itemView.findViewById(R.id.nationalityPlayer);
            imbDetail = (ImageButton) itemView.findViewById(R.id.btnSeeDetail);
            imbDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayerDetail.class);
                    intent.putExtra("playerId", listMember.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
