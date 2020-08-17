package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.AddReferee;
import com.example.qlgiaibongda.model.Referee;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListRefereeManagementAdapter extends RecyclerView.Adapter<ListRefereeManagementAdapter.RefereeViewHolder>  {
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
        if (referee.getAvatar() == null || referee.getAvatar().equals("")) {
            Picasso.get()
                    .load(R.drawable.no_avatar)
                    .into(holder.imgRefereeLogo);
        }
        else {
            Picasso.get()
                    .load(referee.getAvatar())
                    .error(R.drawable.no_avatar)
                    .into(holder.imgRefereeLogo);
        }
        holder.tvRefereeName.setText(referee.getName());

        holder.setmOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {
                Toast.makeText(context,referee.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddReferee.class);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listReferee.size();
    }

    public void filterList(ArrayList<Referee> filteredList) {
        listReferee = filteredList;
        notifyDataSetChanged();
    }


    public class RefereeViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgRefereeLogo;
        public TextView tvRefereeName;
        public onItemClickListener itemClickListener;


        public void setmOnItemClickListener (onItemClickListener _onItemClickListener){
            this.itemClickListener = _onItemClickListener;
        }

        public RefereeViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            imgRefereeLogo = (ImageView) itemView.findViewById(R.id.refereeManageRowLogoImageView);
            tvRefereeName = (TextView) itemView.findViewById(R.id.refereeManageRowNameTextView);
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
