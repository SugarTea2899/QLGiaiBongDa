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
import com.example.qlgiaibongda.model.Coach;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListCoachManagementAdapter extends RecyclerView.Adapter<ListCoachManagementAdapter.CoachViewHolder> {
    public ArrayList<Coach> listCoach;
    private Context context;
    private onItemClickListener mOnItemClickListener;

    public ListCoachManagementAdapter(ArrayList<Coach> listCoach, Context context, onItemClickListener mOnItemClickListener) {
        this.listCoach = listCoach;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public CoachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coach_management_row, parent, false);
        return new CoachViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoachViewHolder holder, int position) {
        Coach coach = listCoach.get(position);
        holder.imgCoachLogo.setImageResource(R.drawable.old_trafford);
        holder.tvCoachName.setText("Manchester City");
    }

    @Override
    public int getItemCount() {
        return listCoach.size();
    }

    public class CoachViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CircleImageView imgCoachLogo;
        public TextView tvCoachName;
        public onItemClickListener itemClickListener;


        public CoachViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imgCoachLogo = (CircleImageView) itemView.findViewById(R.id.coachManageRowLogoImageView);
            tvCoachName = (TextView) itemView.findViewById(R.id.coachManageRowNameTextView);
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
