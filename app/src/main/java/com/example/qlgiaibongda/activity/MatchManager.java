package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMatchAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qlgiaibongda.state.State.isLogined;

public class MatchManager extends AppCompatActivity implements ListMatchAdapter.onItemClickListener{
    private RecyclerView rcvListMatches;
    private ListMatchAdapter listMatchAdapter;
    private ArrayList<Match> filterList = new ArrayList<>();
    private ArrayList<Match> listMatch;
    private EditText edtFilter;
    private ImageButton imbAddMatch;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_manager);

        getWidget();
        setEvent("");
    }

    private void filter(String s) {
        filterList.clear();
        for (Match match : listMatch) {
            if (match.getHomeTeam().toLowerCase().contains(s.toLowerCase().trim()) || match.getGuestTeam().toLowerCase().contains(s.toLowerCase().trim())) {
                filterList.add(match);
            }
        }
        listMatchAdapter.filterList(filterList);
    }

    private void getWidget() {
        rcvListMatches = (RecyclerView) findViewById(R.id.listMatches);
        rcvListMatches.setLayoutManager(new LinearLayoutManager(MatchManager.this));
        listMatch = new ArrayList<>();
        listMatchAdapter = new ListMatchAdapter(listMatch, this, this);
        rcvListMatches.setAdapter(listMatchAdapter);
        tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText("Danh sách trận đấu");
        imbAddMatch = (ImageButton) findViewById(R.id.addMatchImg);
        if (isLogined) {
            imbAddMatch.setVisibility(View.VISIBLE);
        }

        edtFilter = (EditText) findViewById(R.id.searchText);
        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        imbAddMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMatch.class);
                startActivity(intent);
            }
        });
    }

    private void setEvent(String s) {
        DataClient dataClient = APIUtils.getData();
        Call<List<Match>> getListMatch = dataClient.getListSearchMatch(s);
        getListMatch.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    listMatch = (ArrayList<Match>) response.body();
                    listMatchAdapter = new ListMatchAdapter(listMatch, MatchManager.this, MatchManager.this);
                    rcvListMatches.setAdapter(listMatchAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Log.d("error", "onFailure getListMatch");
            }
        });
    }

    @Override
    public void onItemClick(View v, int i) {

    }
}
