package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import com.example.qlgiaibongda.R;

import com.example.qlgiaibongda.adapter.ItemDrawer;
import com.example.qlgiaibongda.adapter.ItemDrawerAdapter;
import com.example.qlgiaibongda.adapter.ItemMatchRound;
import com.example.qlgiaibongda.adapter.ItemMatchRoundAdapter;
import com.example.qlgiaibongda.adapter.ItemRankTeam;
import com.example.qlgiaibongda.adapter.ItemRankTeamAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mainDrawerLayout;
    private NavigationView mainNavigationView;
    private ListView menuListView;
    private TextView roundNumberTextView;
    private ListView roundMatchListView;
    private ListView topRankedListView;
    ArrayList<ItemDrawer> itemDrawerArrayList;
    ArrayList<ItemMatchRound> itemMatchRoundArrayList;
    ArrayList<ItemRankTeam> itemRankTeamArrayList;
    ItemDrawerAdapter itemDrawerAdapter;
    ItemMatchRoundAdapter itemMatchRoundAdapter;
    ItemRankTeamAdapter itemRankTeamAdapter;

    private TextView justTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        actionToolbar();
        actionMainMenu();
        addMatchAndRound();
        addTopTeam();


        //test here
          justTest = (TextView) findViewById(R.id.roundNumberTextView);

          justTest.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, AddPlayer.class);
                  startActivity(intent);
              }
          });

        //-------------------
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void actionMainMenu() {
        itemDrawerArrayList = new ArrayList<>();
        itemDrawerArrayList.add(new ItemDrawer("Tìm kiếm", R.drawable.ic_action_search2));
        itemDrawerAdapter = new ItemDrawerAdapter(this, R.layout.drawer_item_row,itemDrawerArrayList);
        menuListView.setAdapter(itemDrawerAdapter);
    }

    private void addMatchAndRound() {
        itemMatchRoundArrayList = new ArrayList<>();

        itemMatchRoundArrayList.add(new ItemMatchRound("MU", "MC","22:00 31/7", R.drawable.ic_premier_league,R.drawable.ic_premier_league,R.drawable.ic_premier_league));
        itemMatchRoundArrayList.add(new ItemMatchRound("MU", "MC","22:00 31/7", R.drawable.ic_premier_league,R.drawable.ic_premier_league,R.drawable.ic_premier_league));
        itemMatchRoundArrayList.add(new ItemMatchRound("MU", "MC","22:00 31/7", R.drawable.ic_premier_league,R.drawable.ic_premier_league,R.drawable.ic_premier_league));
        itemMatchRoundArrayList.add(new ItemMatchRound("MU", "MC","22:00 31/7", R.drawable.ic_premier_league,R.drawable.ic_premier_league,R.drawable.ic_premier_league));

        itemMatchRoundAdapter = new ItemMatchRoundAdapter(this, R.layout.round_match_row,itemMatchRoundArrayList);
        roundMatchListView.setAdapter(itemMatchRoundAdapter);
    }

    private void addTopTeam() {
        itemRankTeamArrayList = new ArrayList<>();


        itemRankTeamArrayList.add(new ItemRankTeam("1", R.drawable.ic_up_rank, R.drawable.ic_premier_league, "Man United", "38", "-20", "100"));
        itemRankTeamArrayList.add(new ItemRankTeam("1", R.drawable.ic_up_rank, R.drawable.ic_premier_league, "Man United", "38", "-20", "100"));
        itemRankTeamArrayList.add(new ItemRankTeam("1", R.drawable.ic_up_rank, R.drawable.ic_premier_league, "Man United", "38", "-20", "100"));
        itemRankTeamArrayList.add(new ItemRankTeam("1", R.drawable.ic_up_rank, R.drawable.ic_premier_league, "Man United", "38", "-20", "100"));
        itemRankTeamArrayList.add(new ItemRankTeam("1", R.drawable.ic_up_rank, R.drawable.ic_premier_league, "Man United", "38", "-20", "100"));


        itemRankTeamAdapter = new ItemRankTeamAdapter(this, R.layout.ranked_team_row,itemRankTeamArrayList);
        topRankedListView.setAdapter(itemRankTeamAdapter);

    }

    private void getWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        mainNavigationView = (NavigationView) findViewById(R.id.navigationView);
        menuListView = (ListView) findViewById(R.id.menuListView);
        roundNumberTextView = (TextView) findViewById(R.id.roundNumberTextView);
        roundMatchListView = (ListView) findViewById(R.id.roundMatchListView);
        topRankedListView = (ListView) findViewById(R.id.topRankedListView);
    }
}