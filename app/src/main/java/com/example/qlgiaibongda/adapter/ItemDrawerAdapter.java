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

        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(itemDrawerList.get(i).itemName);
        viewHolder.imageView.setImageResource(itemDrawerList.get(i).itemIcon);

        return view;
    }


}
