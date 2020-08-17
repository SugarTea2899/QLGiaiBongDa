package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlgiaibongda.R;

import com.example.qlgiaibongda.adapter.ItemDrawer;
import com.example.qlgiaibongda.adapter.ItemDrawerAdapter;
import com.example.qlgiaibongda.adapter.ListMatchRoundAdapter;
import com.example.qlgiaibongda.adapter.ListRankingAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Rank;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListMatchRoundAdapter.onItemClickListener, ListRankingAdapter.onItemClickListener {

    private Context context = this;
    private Toolbar toolbar;
    private DrawerLayout mainDrawerLayout;
    private NavigationView mainNavigationView;
    private ListView menuListView;
    private TextView roundNumberTextView;
    private RecyclerView roundMatchRecyclerView;
    private RecyclerView topRankedRecyclerView;
    private ImageButton imgButtonListFullMatch;
    private ImageButton imgButtonListFullRanked;
    ArrayList<ItemDrawer> itemDrawerArrayList;
    ArrayList<Match> itemMatchRoundArrayList = new ArrayList<Match>();
    ArrayList<Team> itemRankTeamArrayList = new ArrayList<Team>();
    ItemDrawerAdapter itemDrawerAdapter;
    ListMatchRoundAdapter itemMatchRoundAdapter ;
    ListRankingAdapter itemRankTeamAdapter;

    public static HashMap<String, String> teamIdToTeamNameHashMap = new HashMap<String, String>();
    public static HashMap<String, Team> teamNameToTeamHashMap = new HashMap<String, Team>();
    public static HashMap<String, Rank> teamNameToRankHashMap = new HashMap<String, Rank>();

    private TextView justTest;
    DataClient dataClient = APIUtils.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        setupTeamHashMap();
        actionToolbar();
        actionMainMenu();

        imgButtonListFullMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatchManager.class);
                startActivity(intent);
            }
        });

        imgButtonListFullRanked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });

        //test here
        justTest = (TextView) findViewById(R.id.roundNumberTextView);
        justTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerManagement.class);
                startActivity(intent);
            }
        });


        //-------------------



        addMatchAndRound();
        //addTopTeam();
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

    private void setupTeamHashMap() {
        DataClient dataClient = APIUtils.getData();

        Call<ArrayList<Team>> getListTeam = dataClient.getListSearchTeam("");
        Context context = this;
        getListTeam.enqueue(new Callback<ArrayList<Team>>() {
            @Override
            public void onResponse(Call<ArrayList<Team>> call, Response<ArrayList<Team>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Team> teams = (ArrayList<Team>) response.body();
                    for (Team team : teams) {
                        teamIdToTeamNameHashMap.put(team.getId(), team.getName());
                        teamNameToTeamHashMap.put(team.getName(),team);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Team>> call, Throwable t) {

            }
        });


    }


    private void actionMainMenu() {
        itemDrawerArrayList = new ArrayList<>();
        itemDrawerArrayList.add(new ItemDrawer("Tìm kiếm", R.drawable.ic_action_search2));
        itemDrawerAdapter = new ItemDrawerAdapter(this, R.layout.drawer_item_row, itemDrawerArrayList);
        menuListView.setAdapter(itemDrawerAdapter);
    }

    private void addMatchAndRound() {
        Call<Match> currentRoundCall = dataClient.getCurrentRound();
        currentRoundCall.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                Match match = response.body();
                roundNumberTextView.setText("Vòng đấu " + match.getRound());
                DataClient dataClient1 = APIUtils.getData();

                Call<ArrayList<Match>> currentMatchCall = dataClient1.getListCurrentMatch(match.getRound());
                currentMatchCall.enqueue(new Callback<ArrayList<Match>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Match>> call, Response<ArrayList<Match>> response) {
                        ArrayList<Match> list = (ArrayList<Match>) response.body();
                        itemMatchRoundArrayList = list;
                        roundMatchRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                        itemMatchRoundAdapter = new ListMatchRoundAdapter(itemMatchRoundArrayList, MainActivity.this, MainActivity.this);
                        roundMatchRecyclerView.setAdapter(itemMatchRoundAdapter);

                        addTopTeam();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Match>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Lỗi không thể tải lên danh sách trận", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi không thể lấy được vòng đấu", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addTopTeam() {
        DataClient dataClient = APIUtils.getData();
        Call<ArrayList<Rank>> currentRankCall = dataClient.getCurrentRank(2020);
        currentRankCall.enqueue(new Callback<ArrayList<Rank>>() {
            @Override
            public void onResponse(Call<ArrayList<Rank>> call, Response<ArrayList<Rank>> response) {
                ArrayList<Rank> list = response.body();
                for(int i = 0; i < 3; i++)
                {
                    itemRankTeamArrayList.add(teamNameToTeamHashMap.get(list.get(i).getTeam()));
                }
                for(Rank rank: list)
                {
                    teamNameToRankHashMap.put(rank.getTeam(),rank);
                }
                topRankedRecyclerView.setLayoutManager((new LinearLayoutManager(context)));
                itemRankTeamAdapter = new ListRankingAdapter(itemRankTeamArrayList, MainActivity.this, MainActivity.this);
                topRankedRecyclerView.setAdapter(itemRankTeamAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Rank>> call, Throwable t) {

            }
        });



    }

    private void getWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        mainNavigationView = (NavigationView) findViewById(R.id.navigationView);
        menuListView = (ListView) findViewById(R.id.menuListView);
        roundNumberTextView = (TextView) findViewById(R.id.roundNumberTextView);
        roundMatchRecyclerView = (RecyclerView) findViewById(R.id.roundMatchRecyclerView);
        topRankedRecyclerView = (RecyclerView) findViewById(R.id.topRankedRecyclerView);
        imgButtonListFullMatch = (ImageButton) findViewById(R.id.listMatchHistoryImageButton);
        imgButtonListFullRanked = (ImageButton) findViewById(R.id.listFullRankedImageButton);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(View v, int i) {

    }
}