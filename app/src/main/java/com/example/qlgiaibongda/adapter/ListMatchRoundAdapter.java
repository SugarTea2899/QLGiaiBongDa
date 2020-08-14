package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.MainActivity;
import com.example.qlgiaibongda.model.Match;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListMatchRoundAdapter extends RecyclerView.Adapter<ListMatchRoundAdapter.MatchViewHolder> {

    public ArrayList<Match> listMatch;
    public ArrayList<Match> listResult;
    private Context context;
    private onItemClickListener mOnItemClickListener;


    public ListMatchRoundAdapter(ArrayList<Match> listMatch, Context context, onItemClickListener mOnItemClickListener) {
        this.listMatch = listMatch;
        this.listResult = new ArrayList<>(listMatch);
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.round_match_row, parent, false);
        return new MatchViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = listMatch.get(position);
        holder.textViewHomeName.setText("MU");
        holder.textViewAwayName.setText("BIR");

        Timestamp matchDayTimeStamp = new Timestamp(System.currentTimeMillis());
        Date matchDate = new Date(matchDayTimeStamp.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM hh:mm");
        holder.textViewTimeInfo.setText(simpleDateFormat.format(matchDate));

        holder.imgHome.setImageResource(R.drawable.manutd);
        holder.imgAway.setImageResource(R.drawable.manutd);
        holder.imgMoreInfo.setImageResource(R.drawable.ic_more_info);

        holder.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imgAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imgMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return listMatch.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewHomeName;
        public TextView textViewAwayName;
        public TextView textViewTimeInfo;
        public ImageButton imgHome;
        public ImageButton imgAway;
        public ImageButton imgMoreInfo;
        public onItemClickListener itemClickListener;

        public MatchViewHolder(@NonNull View itemView, onItemClickListener itemClickListener) {
            super(itemView);
            textViewHomeName = (TextView) itemView.findViewById(R.id.homeTeamNameText);
            textViewAwayName = (TextView) itemView.findViewById(R.id.awayTeamNameText);
            textViewTimeInfo = (TextView) itemView.findViewById(R.id.matchTimeInfoText);
            imgHome = (ImageButton) itemView.findViewById(R.id.homeTeamLogoImage);
            imgAway = (ImageButton) itemView.findViewById(R.id.awayTeamLogoImage);
            imgMoreInfo = (ImageButton) itemView.findViewById(R.id.matchMoreInfoImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          //  itemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface onItemClickListener {
        void onItemClick(int i);
    }
//    private Context context;
//    private int layout;
//    private List<ItemMatchRound> itemMatchRoundList;
//
//    public ListMatchRoundAdapter(Context context, int layout, List<ItemMatchRound> itemMatchRoundList) {
//        this.context = context;
//        this.layout = layout;
//        this.itemMatchRoundList = itemMatchRoundList;
//    }
//    private class ViewHolder {
//        TextView textViewHomeName;
//        TextView textViewAwayName;
//        TextView textViewTimeInfo;
//        ImageButton imgHome;
//        ImageButton imgAway;
//        ImageButton imgMoreInfo;
//
//    }
//    @Override
//    public int getCount() {
//        return itemMatchRoundList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//        if (view == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(layout, null);
//            viewHolder = new ViewHolder();
//            viewHolder.textViewHomeName = (TextView) view.findViewById(R.id.homeTeamNameText);
//            viewHolder.textViewAwayName = (TextView) view.findViewById(R.id.awayTeamNameText);
//            viewHolder.textViewTimeInfo = (TextView) view.findViewById(R.id.matchTimeInfoText);
//            viewHolder.imgHome = (ImageButton) view.findViewById(R.id.homeTeamLogoImage);
//            viewHolder.imgAway = (ImageButton) view.findViewById(R.id.awayTeamLogoImage);
//            viewHolder.imgMoreInfo = (ImageButton) view.findViewById(R.id.matchMoreInfoImage);
//            view.setTag(viewHolder);
//        }
//        else
//        {
//            viewHolder = (ViewHolder) view.getTag();
//        }
//
//
//        viewHolder.textViewHomeName.setText(itemMatchRoundList.get(i).textHomeTeamName);
//        viewHolder.textViewAwayName.setText(itemMatchRoundList.get(i).textAwayTeamName);
//        viewHolder.textViewTimeInfo.setText(itemMatchRoundList.get(i).textMatchTimeInfo);
//        viewHolder.imgHome.setImageResource(itemMatchRoundList.get(i).imgHomeTeam);
//        viewHolder.imgAway.setImageResource(itemMatchRoundList.get(i).imgAwayTeam);
//        viewHolder.imgMoreInfo.setImageResource(itemMatchRoundList.get(i).imgMatchMoreInfo);
//
//        return view;
//    }

}