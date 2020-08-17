package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.activity.CoachManagement;
import com.example.qlgiaibongda.activity.Login;
import com.example.qlgiaibongda.activity.MainActivity;
import com.example.qlgiaibongda.activity.MatchManager;
import com.example.qlgiaibongda.activity.PlayerManagement;
import com.example.qlgiaibongda.activity.RefereeManagement;
import com.example.qlgiaibongda.activity.TeamManagement;

import java.util.ArrayList;
import java.util.List;

public class ItemDrawerAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemDrawer> itemDrawerList;

    public ItemDrawerAdapter(Context context, int layout, List<ItemDrawer> itemDrawerList) {
        this.context = context;
        this.layout = layout;
        this.itemDrawerList = itemDrawerList;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
    @Override
    public int getCount() {
        return itemDrawerList.size();
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
            viewHolder.textView = (TextView) view.findViewById(R.id.textViewMainMenuItem);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imgViewMainMenuItem);
            view.setTag(viewHolder);

            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.textView.getText().equals("Đăng nhập")) {
                        Intent intent = new Intent(context, Login.class);
                        context.startActivity(intent);
                    }
                    else if (viewHolder.textView.getText().equals("Danh sách trận đấu")) {
                        Intent intent = new Intent(context, MatchManager.class);
                        context.startActivity(intent);
                    }
                    else if (viewHolder.textView.getText().equals("Danh sách đội bóng")) {
                        Intent intent = new Intent(context, TeamManagement.class);
                        context.startActivity(intent);
                    }
                    else if (viewHolder.textView.getText().equals("Danh sách cầu thủ")) {
                        Intent intent = new Intent(context, PlayerManagement.class);
                        context.startActivity(intent);
                    }
                    else if (viewHolder.textView.getText().equals("Danh sách huấn luyện viên")) {
                        Intent intent = new Intent(context, CoachManagement.class);
                        context.startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(context, RefereeManagement.class);
                        context.startActivity(intent);
                    }
                }
            });

        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(itemDrawerList.get(i).itemName);

        return view;
    }


}
