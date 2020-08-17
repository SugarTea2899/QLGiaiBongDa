package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListPlayerStatisticAdapter;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsLeague extends AppCompatActivity{
    private RecyclerView rcvListGoals;
    private RecyclerView rcvListYellows;
    private RecyclerView rcvListReds;
    private ListPlayerStatisticAdapter listPlayerStatisticAdapter;
    private ArrayList<Player> allPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_league);

        getWidgets();
        setEvents();
    }

    private void getWidgets() {
        rcvListGoals = (RecyclerView) findViewById(R.id.rcvListGoalStatistic);
        rcvListGoals.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));

        rcvListYellows = (RecyclerView) findViewById(R.id.rcvListYellowStatistic);
        rcvListYellows.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));

        rcvListReds = (RecyclerView) findViewById(R.id.rcvListRedStatistic);
        rcvListReds.setLayoutManager(new LinearLayoutManager(StatisticsLeague.this));
    }

    private void setEvents() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Player>> callBackAllPlayer = dataClient.getAllPlayer();
        callBackAllPlayer.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Player> temp = new ArrayList<>();
                    allPlayer = (ArrayList<Player>) response.body();
                    Collections.sort(allPlayer, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            if (o1.getTotalGoal() < o2.getTotalGoal())
                                return 1;
                            else if (o1.getTotalGoal() > o2.getTotalGoal())
                                return -1;
                            return 0;
                        }
                    });
                    for (int i = 0; i < 5; i++) temp.add(allPlayer.get(i));
                    listPlayerStatisticAdapter = new ListPlayerStatisticAdapter(temp, StatisticsLeague.this);
                    rcvListGoals.setAdapter(listPlayerStatisticAdapter);

                    Collections.sort(allPlayer, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            if (o1.getTotalYellowCard() < o2.getTotalYellowCard())
                                return 1;
                            else if (o1.getTotalYellowCard() > o2.getTotalYellowCard())
                                return -1;
                            return 0;
                        }
                    });
                    temp.clear();
                    for (int i = 0; i < 5; i++) temp.add(allPlayer.get(i));
                    listPlayerStatisticAdapter = new ListPlayerStatisticAdapter(temp, StatisticsLeague.this);
                    rcvListYellows.setAdapter(listPlayerStatisticAdapter);

                    Collections.sort(allPlayer, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            if (o1.getTotalRedCard() < o2.getTotalRedCard())
                                return 1;
                            else if (o1.getTotalRedCard() > o2.getTotalRedCard())
                                return -1;
                            return 0;
                        }
                    });
                    temp.clear();
                    for (int i = 0; i < 5; i++) temp.add(allPlayer.get(i));
                    listPlayerStatisticAdapter = new ListPlayerStatisticAdapter(temp, StatisticsLeague.this);
                    rcvListReds.setAdapter(listPlayerStatisticAdapter);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Không tìm thấy", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
