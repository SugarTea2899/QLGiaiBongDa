package com.example.qlgiaibongda.adapter;

import android.content.Context;
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
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class ListMemberClubAdapter extends RecyclerView.Adapter<ListMemberClubAdapter.MemberViewHolder> {
    public ArrayList<Player> listMember;
    private Context context;
    private onItemClickListener mOnItemClickListener;
    public ListMemberClubAdapter(ArrayList<Player> listPlayer, Context context, onItemClickListener mOnItemClickListener) {
        this.listMember = listPlayer;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_member_item, parent, false);
        return new MemberViewHolder(itemView, mOnItemClickListener);
    }

    public void onBindViewHolder(@NonNull ListMemberClubAdapter.MemberViewHolder holder, int position) {
        Player player = listMember.get(position);
        holder.tvNumber.setText(player.getNumber().toString());
        holder.tvNamePlayer.setText(player.getName());
        holder.imvPlayer.setImageResource(R.drawable.old_trafford);
        holder.tvNationality.setText(player.getNationality());
    }

    @Override
    public int getItemCount() {
        return listMember.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvNumber;
        public TextView tvNamePlayer;
        public ImageView imvPlayer;
        public TextView tvNationality;
        public ImageButton imbDetail;
        public onItemClickListener itemClickListener;

        public MemberViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.numberPlayer);
            tvNamePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            imvPlayer = (ImageView) itemView.findViewById(R.id.imageOfPlayer);
            tvNationality = (TextView) itemView.findViewById(R.id.nationalityPlayer);
            imbDetail = (ImageButton) itemView.findViewById(R.id.btnSeeDetail);
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
