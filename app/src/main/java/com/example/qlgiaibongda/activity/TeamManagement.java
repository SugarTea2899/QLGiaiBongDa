package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListTeamManagementAdapter;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.example.qlgiaibongda.state.State;
import com.google.android.material.appbar.AppBarLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListTeamManagementAdapter.onItemClickListener {

    private Context context;
    private RecyclerView recyclerViewTeamManagement;
    private ImageButton imgButtonAddTeam;
    private EditText edtTeamManagementSearch;
    ArrayList<Team> listTeamManagementItem;
    private ListTeamManagementAdapter listTeamManagementAdapter;
    private AppBarLayout appBarLayoutTeamManagement;
    private TextView toolbarTitleTeamManagement;
    private DataClient dataClient = APIUtils.getData();
    ArrayList<Team> filteredList = new ArrayList<>();

    private ImageButton imgBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_management);

        getWidget();
        if (!State.isLogined)
        {
            imgButtonAddTeam.setVisibility(View.INVISIBLE);
        }
        setEvent("");
    }

    private void setEvent(String searchText) {

        Call<ArrayList<Team>> getListTeam = dataClient.getListSearchTeam(searchText);
        Context context = this;
        getListTeam.enqueue(new Callback<ArrayList<Team>>() {
            @Override
            public void onResponse(Call<ArrayList<Team>> call, Response<ArrayList<Team>> response) {
                if (response.isSuccessful()) {
                    listTeamManagementItem = (ArrayList<Team>)response.body();
                    recyclerViewTeamManagement.setLayoutManager(new LinearLayoutManager(context));
                    listTeamManagementAdapter = new ListTeamManagementAdapter(listTeamManagementItem,TeamManagement.this, TeamManagement.this);
                    recyclerViewTeamManagement.setAdapter(listTeamManagementAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Team>> call, Throwable t) {

            }
        });
    }

    private void filter(String s) {
        filteredList.clear();
        for(Team team : listTeamManagementItem) {
            if (team.getName().toLowerCase().contains(s.toLowerCase().trim())) {
                filteredList.add(team);
            }
        }
        listTeamManagementAdapter.filterList(filteredList);
    }


    protected void getWidget() {
        context = this;

        recyclerViewTeamManagement = (RecyclerView) findViewById(R.id.teamManagementRecyclerView);
        imgButtonAddTeam = (ImageButton) findViewById(R.id.addTeamButton);
        edtTeamManagementSearch = (EditText) findViewById(R.id.teamManagementSearchEditText);
        appBarLayoutTeamManagement = (AppBarLayout) findViewById(R.id.appbarTeamManagement);
        toolbarTitleTeamManagement = (TextView) findViewById(R.id.title);
        toolbarTitleTeamManagement.setText(R.string.team_management);
        imgBackButton = (ImageButton) findViewById(R.id.backBtn);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgButtonAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTeam.class);
                startActivity(intent);
            }
        });


        listTeamManagementItem = new ArrayList<Team>();
        recyclerViewTeamManagement.setLayoutManager(new LinearLayoutManager(this));
        listTeamManagementAdapter = new ListTeamManagementAdapter(listTeamManagementItem,TeamManagement.this, TeamManagement.this);
        recyclerViewTeamManagement.setAdapter(listTeamManagementAdapter);


        edtTeamManagementSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    @Override
    public void onItemClick(View v, int i) {
       // Team team = filteredList.get(i);
       // Toast.makeText(this,team.getName(),Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // Team team = filteredList.get(i);
       // Toast.makeText(this,team.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
