package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemMatchRoundAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemMatchRound> itemMatchRoundList;

    public ItemMatchRoundAdapter(Context context, int layout, List<ItemMatchRound> itemMatchRoundList) {
        this.context = context;
        this.layout = layout;
        this.itemMatchRoundList = itemMatchRoundList;
    }
    private class ViewHolder {
        TextView textViewHomeName;
        TextView textViewAwayName;
        TextView textViewTimeInfo;
        ImageView imgHome;
        ImageView imgAway;
        ImageView imgMoreInfo;

    }
    @Override
    public int getCount() {
        return itemMatchRoundList.size();
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
            viewHolder = new ViewHolder();
            viewHolder.textViewHomeName = (TextView) view.findViewById(R.id.homeTeamNameText);
            viewHolder.textViewAwayName = (TextView) view.findViewById(R.id.awayTeamNameText);
            viewHolder.textViewTimeInfo = (TextView) view.findViewById(R.id.matchTimeInfoText);
            viewHolder.imgHome = (ImageView) view.findViewById(R.id.homeTeamLogoImage);
            viewHolder.imgAway = (ImageView) view.findViewById(R.id.awayTeamLogoImage);
            viewHolder.imgMoreInfo = (ImageView) view.findViewById(R.id.matchMoreInfoImage);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.textViewHomeName.setText(itemMatchRoundList.get(i).textHomeTeamName);
        viewHolder.textViewAwayName.setText(itemMatchRoundList.get(i).textAwayTeamName);
        viewHolder.textViewTimeInfo.setText(itemMatchRoundList.get(i).textMatchTimeInfo);
        viewHolder.imgHome.setImageResource(itemMatchRoundList.get(i).imgHomeTeam);
        viewHolder.imgAway.setImageResource(itemMatchRoundList.get(i).imgAwayTeam);
        viewHolder.imgMoreInfo.setImageResource(itemMatchRoundList.get(i).imgMatchMoreInfo);

        return view;
    }
}
