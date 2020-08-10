package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListRankingAdapter;
import com.example.qlgiaibongda.model.Team;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity implements ListRankingAdapter.onItemClickListener {
    private RecyclerView rcvListTeam;
    private ListRankingAdapter listRankingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        getWidget();
        setEvent();
    }

    private void getWidget() {
        ArrayList<Team> test = new ArrayList<>();
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        test.add(new Team());
        listRankingAdapter = new ListRankingAdapter(test, RankingActivity.this, RankingActivity.this);

        rcvListTeam = (RecyclerView) findViewById(R.id.rcvRanking);
        rcvListTeam.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
        rcvListTeam.setAdapter(listRankingAdapter);
    }

    private void setEvent() {

    }

    @Override
    public void onItemClick(int i) {

    }
}
