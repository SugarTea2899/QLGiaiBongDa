package com.example.qlgiaibongda.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qlgiaibongda.activity.ClubStatisticalFragment;
import com.example.qlgiaibongda.activity.ListMatchesFragment;

public class TabLayoutClubDetailAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public TabLayoutClubDetailAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListMatchesFragment listMatchesFragment = new ListMatchesFragment();
                return listMatchesFragment;
            //case 1:
            //    return null;
            case 1:
                ClubStatisticalFragment clubStatisticalFragment = new ClubStatisticalFragment();
                return clubStatisticalFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
