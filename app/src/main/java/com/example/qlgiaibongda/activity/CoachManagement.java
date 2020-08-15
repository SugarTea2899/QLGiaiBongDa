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
import com.example.qlgiaibongda.adapter.ListCoachManagementAdapter;
import com.example.qlgiaibongda.model.Coach;

import java.util.ArrayList;

public class CoachManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListCoachManagementAdapter.onItemClickListener {

    private RecyclerView recyclerViewCoachManagement;
    private ImageButton imgButtonAddCoach;
    private EditText edtCoachManagementSearch;
    ArrayList<Coach> listCoachManagementItem;
    private ListCoachManagementAdapter listCoachManagementAdapter;
    private TextView toolbarTitleCoachManagement;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_management);

        getWidget();

    }



    protected void getWidget() {
        recyclerViewCoachManagement = (RecyclerView) findViewById(R.id.coachManagementRecyclerView);
        imgButtonAddCoach = (ImageButton) findViewById(R.id.addCoachButton);
        edtCoachManagementSearch = (EditText) findViewById(R.id.coachManagementSearchEditText);
        toolbarTitleCoachManagement = (TextView) findViewById(R.id.title);
        toolbarTitleCoachManagement.setText(R.string.coach_management);
        
        listCoachManagementItem = new ArrayList<Coach>();
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());
        listCoachManagementItem.add(new Coach());

        recyclerViewCoachManagement.setLayoutManager(new LinearLayoutManager(this));
        listCoachManagementAdapter = new ListCoachManagementAdapter(listCoachManagementItem,CoachManagement.this, CoachManagement.this);
        recyclerViewCoachManagement.setAdapter(listCoachManagementAdapter);
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