package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListPlayerStatisticAdapter;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class StatisticsLeague extends AppCompatActivity implements ListPlayerStatisticAdapter.onItemClickListener{
    private RecyclerView rcvListGoals;
    private RecyclerView rcvListYellows;
    private RecyclerView rcvListReds;
    private ListPlayerStatisticAdapter listPlayerStatisticAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_league);

        getWidgets();
        setEvents();
    }

    private void getWidgets() {
        ArrayList<Player> test = new ArrayList<>();
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        listPlayerStatisticAdapter = new ListPlayerStatisticAdapter(test, StatisticsLeague.this, StatisticsLeague.this);

        rcvListGoals = (RecyclerView) findViewById(R.id.rcvListGoalStatistic);
        rcvListGoals.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));
        rcvListGoals.setAdapter(listPlayerStatisticAdapter);

        rcvListYellows = (RecyclerView) findViewById(R.id.rcvListYellowStatistic);
        rcvListYellows.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));
        rcvListYellows.setAdapter(listPlayerStatisticAdapter);

        rcvListReds = (RecyclerView) findViewById(R.id.rcvListRedStatistic);
        rcvListReds.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));
        rcvListReds.setAdapter(listPlayerStatisticAdapter);
    }

    private void setEvents() {

    }

    @Override
    public void onItemClick(int i) {

    }
}
