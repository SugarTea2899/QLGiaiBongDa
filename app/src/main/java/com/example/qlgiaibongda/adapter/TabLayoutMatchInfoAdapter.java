package com.example.qlgiaibongda.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qlgiaibongda.activity.ClubStatisticalFragment;
import com.example.qlgiaibongda.activity.ListMatchesFragment;
import com.example.qlgiaibongda.activity.ListMemberClubFragment;
import com.example.qlgiaibongda.activity.MatchProgressFragment;
import com.example.qlgiaibongda.activity.SelectedList;
import com.example.qlgiaibongda.activity.SelectedListFragment;
import com.example.qlgiaibongda.activity.StatisticMatchFragment;

public class TabLayoutMatchInfoAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public TabLayoutMatchInfoAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MatchProgressFragment matchProgressFragment = new MatchProgressFragment();
                return matchProgressFragment;
            case 1:
                SelectedListFragment selectedListFragment = new SelectedListFragment();
                return selectedListFragment;
            case 2:
                StatisticMatchFragment statisticMatchFragment = new StatisticMatchFragment();
                return statisticMatchFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
