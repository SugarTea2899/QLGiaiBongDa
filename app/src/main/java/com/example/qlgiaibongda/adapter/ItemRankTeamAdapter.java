package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlgiaibongda.R;

import java.util.List;

public class ItemRankTeamAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemRankTeam> itemRankTeamList;

    public ItemRankTeamAdapter(Context context, int layout, List<ItemRankTeam> itemRankTeamList) {
        this.context = context;
        this.layout = layout;
        this.itemRankTeamList = itemRankTeamList;
    }

    private class ViewHolder {
        TextView textViewRankedNumber;
        ImageView imageViewStatusTeam;
        ImageView imageViewTeamLogo;
        TextView textViewTeamName;
        TextView textViewRoundPlayedNumber;
        TextView textViewGoalDiff;
        TextView textViewPoint;
    }

    @Override
    public int getCount() {
        return itemRankTeamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder = new ItemRankTeamAdapter.ViewHolder();
            viewHolder.textViewRankedNumber = (TextView) view.findViewById(R.id.rankedNumberTextView);
            viewHolder.imageViewStatusTeam = (ImageView) view.findViewById(R.id.statusTeamImageView);
            viewHolder.imageViewTeamLogo = (ImageView) view.findViewById(R.id.teamRankedLogoImageView);
            viewHolder.textViewTeamName = (TextView) view.findViewById(R.id.teamRankedNameTextView);
            viewHolder.textViewRoundPlayedNumber = (TextView) view.findViewById(R.id.roundPlayedTextView);
            viewHolder.textViewGoalDiff = (TextView) view.findViewById(R.id.goalDiffTextView);
            viewHolder.textViewPoint = (TextView) view.findViewById(R.id.teamPointTextView);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textViewRankedNumber.setText(itemRankTeamList.get(i).textRank);
        viewHolder.imageViewStatusTeam.setImageResource(itemRankTeamList.get(i).imgStatus);
        viewHolder.imageViewTeamLogo.setImageResource(itemRankTeamList.get(i).imgTeamLogo);
        viewHolder.textViewTeamName.setText(itemRankTeamList.get(i).textTeamName);
        viewHolder.textViewRoundPlayedNumber.setText(itemRankTeamList.get(i).textRound);
        viewHolder.textViewGoalDiff.setText(itemRankTeamList.get(i).textGoalDiff);
        viewHolder.textViewPoint.setText(itemRankTeamList.get(i).textPoint);

        return view;
    }

}
