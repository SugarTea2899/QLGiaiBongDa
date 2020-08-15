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
import com.example.qlgiaibongda.model.Referee;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListRefereeManagementAdapter extends RecyclerView.Adapter<ListRefereeManagementAdapter.RefereeViewHolder> {
    public ArrayList<Referee> listReferee;
    private Context context;
    private onItemClickListener mOnItemClickListener;

    public ListRefereeManagementAdapter(ArrayList<Referee> listReferee, Context context, onItemClickListener mOnItemClickListener) {
        this.listReferee = listReferee;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public RefereeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.referee_management_row, parent, false);
        return new RefereeViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RefereeViewHolder holder, int position) {
        Referee referee = listReferee.get(position);
        holder.imgRefereeLogo.setImageResource(R.drawable.old_trafford);
        holder.tvRefereeName.setText(String.format("Referee %d", position));
    }

    @Override
    public int getItemCount() {
        return listReferee.size();
    }

    public class RefereeViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CircleImageView imgRefereeLogo;
        public TextView tvRefereeName;
        public onItemClickListener itemClickListener;


        public RefereeViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imgRefereeLogo = (CircleImageView) itemView.findViewById(R.id.refereeManageRowLogoImageView);
            tvRefereeName = (TextView) itemView.findViewById(R.id.refereeManageRowNameTextView);
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
