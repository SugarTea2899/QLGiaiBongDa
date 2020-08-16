package com.example.qlgiaibongda.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qlgiaibongda.activity.MatchProgressFragment;
import com.example.qlgiaibongda.activity.SelectedListFragment;
import com.example.qlgiaibongda.activity.StatisticMatchFragment;
import com.example.qlgiaibongda.model.Match;

public class TabLayoutMatchInfoAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    private Match matchInfo;

    public TabLayoutMatchInfoAdapter(Context context, FragmentManager fm, int totalTabs, Match matchInfo) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.matchInfo = matchInfo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MatchProgressFragment matchProgressFragment = new MatchProgressFragment(matchInfo);
                return matchProgressFragment;
            case 1:
                SelectedListFragment selectedListFragment = new SelectedListFragment(matchInfo);
                return selectedListFragment;
            case 2:
                StatisticMatchFragment statisticMatchFragment = new StatisticMatchFragment(matchInfo);
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
