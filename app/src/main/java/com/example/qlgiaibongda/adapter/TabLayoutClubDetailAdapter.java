package com.example.qlgiaibongda.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qlgiaibongda.activity.ClubStatisticalFragment;
import com.example.qlgiaibongda.activity.ListMatchesFragment;
import com.example.qlgiaibongda.activity.ListMemberClubFragment;
import com.example.qlgiaibongda.activity.StatisticMatchFragment;
import com.example.qlgiaibongda.model.Team;

public class TabLayoutClubDetailAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    private Team teamInfo;

    public TabLayoutClubDetailAdapter(Context context, FragmentManager fm, int totalTabs, Team teamInfo) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.teamInfo = teamInfo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListMatchesFragment listMatchesFragment = new ListMatchesFragment(teamInfo);
                return listMatchesFragment;
            case 1:
                ListMemberClubFragment listMemberClubFragment = new ListMemberClubFragment(teamInfo);
                return listMemberClubFragment;
            case 2:
                ClubStatisticalFragment clubStatisticalFragment = new ClubStatisticalFragment(teamInfo);
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
