package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListTeamManagementAdapter;
import com.example.qlgiaibongda.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListTeamManagementAdapter.onItemClickListener {

    private RecyclerView recyclerViewTeamManagement;
    private ImageButton imgButtonAddTeam;
    private EditText edtTeamManagementSearch;
    ArrayList<Team> listTeamManagementItem;
    private ListTeamManagementAdapter listTeamManagementAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_management);

        getWidget();

    }



    protected void getWidget() {
        recyclerViewTeamManagement = (RecyclerView) findViewById(R.id.teamManagementRecyclerView);
        imgButtonAddTeam = (ImageButton) findViewById(R.id.addTeamButton);
        edtTeamManagementSearch = (EditText) findViewById(R.id.teamManagementSearchEditText);
        listTeamManagementItem = new ArrayList<Team>();
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());
        listTeamManagementItem.add(new Team());

        recyclerViewTeamManagement.setLayoutManager(new LinearLayoutManager(this));
        listTeamManagementAdapter = new ListTeamManagementAdapter(listTeamManagementItem,TeamManagement.this, TeamManagement.this);
        recyclerViewTeamManagement.setAdapter(listTeamManagementAdapter);
    }

    @Override
    public void onItemClick(int i) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
