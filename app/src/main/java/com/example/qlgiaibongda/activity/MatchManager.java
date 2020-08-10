package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMatchAdapter;
import com.example.qlgiaibongda.model.Match;

import java.util.ArrayList;

public class MatchManager extends AppCompatActivity implements ListMatchAdapter.onItemClickListener{
    private RecyclerView rcvListMatches;
    private ListMatchAdapter listMatchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_manager);

        getWidget();
        setEvent();
    }

    private void getWidget() {
        ArrayList<Match> test = new ArrayList<>();
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        listMatchAdapter = new ListMatchAdapter(test, MatchManager.this, MatchManager.this);

        rcvListMatches = (RecyclerView) findViewById(R.id.listMatches);
        rcvListMatches.setLayoutManager(new LinearLayoutManager(MatchManager.this));
        rcvListMatches.setAdapter(listMatchAdapter);
    }

    private void setEvent() {

    }

    @Override
    public void onItemClick(int i) {

    }
}
