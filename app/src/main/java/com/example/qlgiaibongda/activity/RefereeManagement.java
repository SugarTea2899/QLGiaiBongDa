package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListRefereeManagementAdapter;
import com.example.qlgiaibongda.model.Referee;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

public class RefereeManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListRefereeManagementAdapter.onItemClickListener {

    private RecyclerView recyclerViewRefereeManagement;
    private ImageButton imgButtonAddReferee;
    private EditText edtRefereeManagementSearch;
    ArrayList<Referee> listRefereeManagementItem;
    private ListRefereeManagementAdapter listRefereeManagementAdapter;
    private AppBarLayout appBarLayoutRefereeManagement;
    private TextView toolbarTitleRefereeManagement;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referee_management);

        getWidget();

    }



    protected void getWidget() {
        recyclerViewRefereeManagement = (RecyclerView) findViewById(R.id.refereeManagementRecyclerView);
        imgButtonAddReferee = (ImageButton) findViewById(R.id.addRefereeButton);
        edtRefereeManagementSearch = (EditText) findViewById(R.id.refereeManagementSearchEditText);
        appBarLayoutRefereeManagement = (AppBarLayout) findViewById(R.id.appbarRefereeManagement);
        toolbarTitleRefereeManagement = (TextView) findViewById(R.id.title);
        toolbarTitleRefereeManagement.setText(R.string.referee_management);


        listRefereeManagementItem = new ArrayList<Referee>();
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());
        listRefereeManagementItem.add(new Referee());

        recyclerViewRefereeManagement.setLayoutManager(new LinearLayoutManager(this));
        listRefereeManagementAdapter = new ListRefereeManagementAdapter(listRefereeManagementItem,RefereeManagement.this, RefereeManagement.this);
        recyclerViewRefereeManagement.setAdapter(listRefereeManagementAdapter);
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

