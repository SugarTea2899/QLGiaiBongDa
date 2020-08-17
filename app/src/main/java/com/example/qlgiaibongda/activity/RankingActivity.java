package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListRankingAdapter;
import com.example.qlgiaibongda.model.Rank;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity implements ListRankingAdapter.onItemClickListener {
    private RecyclerView rcvListTeam;
    private ListRankingAdapter listRankingAdapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        getWidget();
        setEvent();
    }

    private void getWidget() {
        ArrayList<Team> test = new ArrayList<>();
        DataClient dataClient = APIUtils.getData();
        Call<ArrayList<Rank>> currentRankCall = dataClient.getCurrentRank(2020);
        currentRankCall.enqueue(new Callback<ArrayList<Rank>>() {
            @Override
            public void onResponse(Call<ArrayList<Rank>> call, Response<ArrayList<Rank>> response) {
                ArrayList<Rank> list = response.body();
                for(Rank rank:list)
                {
                    test.add(MainActivity.teamNameToTeamHashMap.get(rank.getTeam()));
                }
                for(Rank rank: list)
                {
                    MainActivity.teamNameToRankHashMap.put(rank.getTeam(),rank);
                }
                rcvListTeam.setLayoutManager((new LinearLayoutManager(context)));
                listRankingAdapter = new ListRankingAdapter(test, RankingActivity.this, RankingActivity.this);
                rcvListTeam.setAdapter(listRankingAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Rank>> call, Throwable t) {

            }
        });
        listRankingAdapter = new ListRankingAdapter(test, RankingActivity.this, RankingActivity.this);

        rcvListTeam = (RecyclerView) findViewById(R.id.rcvRanking);
        rcvListTeam.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
        rcvListTeam.setAdapter(listRankingAdapter);
    }

    private void setEvent() {

    }

    @Override
    public void onItemClick(View v, int i) {

    }
}
