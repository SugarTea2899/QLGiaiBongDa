package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qlgiaibongda.R;

import com.example.qlgiaibongda.adapter.ItemDrawer;
import com.example.qlgiaibongda.adapter.ItemDrawerAdapter;
import com.example.qlgiaibongda.adapter.ListMatchRoundAdapter;
import com.example.qlgiaibongda.adapter.ListRankingAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Team;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListMatchRoundAdapter.onItemClickListener, ListRankingAdapter.onItemClickListener {

    private Toolbar toolbar;
    private DrawerLayout mainDrawerLayout;
    private NavigationView mainNavigationView;
    private ListView menuListView;
    private TextView roundNumberTextView;
    private RecyclerView roundMatchRecyclerView;
    private RecyclerView topRankedRecyclerView;
    ArrayList<ItemDrawer> itemDrawerArrayList;
    ArrayList<Match> itemMatchRoundArrayList;
    ArrayList<Team> itemRankTeamArrayList;
    ItemDrawerAdapter itemDrawerAdapter;
    ListMatchRoundAdapter itemMatchRoundAdapter;
    ListRankingAdapter itemRankTeamAdapter;

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
                  Intent intent = new Intent(MainActivity.this, PlayerDetail.class);

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

        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());
        itemMatchRoundArrayList.add(new Match());


        //itemMatchRoundAdapter = new ListMatchRoundAdapter(itemMatchRoundArrayList, MainActivity.this, MainActivity.this);
        roundMatchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemMatchRoundAdapter = new ListMatchRoundAdapter(itemMatchRoundArrayList,MainActivity.this,MainActivity.this);
        roundMatchRecyclerView.setAdapter(itemMatchRoundAdapter);


    }

    private void addTopTeam() {
        itemRankTeamArrayList = new ArrayList<>();
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());
        itemRankTeamArrayList.add(new Team());

        topRankedRecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        itemRankTeamAdapter = new ListRankingAdapter(itemRankTeamArrayList, MainActivity.this, MainActivity.this);
        topRankedRecyclerView.setAdapter(itemRankTeamAdapter);

    }

    private void getWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        mainNavigationView = (NavigationView) findViewById(R.id.navigationView);
        menuListView = (ListView) findViewById(R.id.menuListView);
        roundNumberTextView = (TextView) findViewById(R.id.roundNumberTextView);
        roundMatchRecyclerView = (RecyclerView) findViewById(R.id.roundMatchRecyclerView);
        topRankedRecyclerView = (RecyclerView) findViewById(R.id.topRankedRecyclerView);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(int i) {

    }
}