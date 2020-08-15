package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.TabLayoutMatchInfoAdapter;
import com.google.android.material.tabs.TabLayout;

public class MatchInfo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        toolbar = (Toolbar) findViewById(R.id.toolBarMatchInfo);
        tabLayout = (TabLayout) findViewById(R.id.menuOptions);
        tabLayout.addTab(tabLayout.newTab().setText("DIỄN BIẾN"));
        tabLayout.addTab(tabLayout.newTab().setText("ĐỘI HÌNH"));
        tabLayout.addTab(tabLayout.newTab().setText("THÔNG SỐ"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        final TabLayoutMatchInfoAdapter adapter = new TabLayoutMatchInfoAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
