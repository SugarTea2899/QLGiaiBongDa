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
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListCoachManagementAdapter;
import com.example.qlgiaibongda.model.Coach;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListCoachManagementAdapter.onItemClickListener {

    private Context context = this;
    private RecyclerView recyclerViewCoachManagement;
    private ImageButton imgButtonAddCoach;
    private EditText edtCoachManagementSearch;
    ArrayList<Coach> listCoachManagementItem;
    private ListCoachManagementAdapter listCoachManagementAdapter;
    private TextView toolbarTitleCoachManagement;
    private DataClient dataClient = APIUtils.getData();
    ArrayList<Coach> filteredList = new ArrayList<>();

    private ImageButton imgBackButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_management);

        getWidget();
        setEvent("");

    }

    private void setEvent(String searchText) {
        Call<ArrayList<Coach>> getListCoach = dataClient.getListSearchCoach(searchText);
        Context context = this;
        getListCoach.enqueue(new Callback<ArrayList<Coach>>() {
            @Override
            public void onResponse(Call<ArrayList<Coach>> call, Response<ArrayList<Coach>> response) {
                if (response.isSuccessful()) {
                    listCoachManagementItem = (ArrayList<Coach>)response.body();
                    recyclerViewCoachManagement.setLayoutManager(new LinearLayoutManager(context));
                    listCoachManagementAdapter = new ListCoachManagementAdapter(listCoachManagementItem,CoachManagement.this, CoachManagement.this);
                    recyclerViewCoachManagement.setAdapter(listCoachManagementAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Coach>> call, Throwable t) {
                System.out.println("toang");
            }
        });

    }

    private void filter(String s) {
        filteredList.clear();
        for(Coach coach : listCoachManagementItem) {
            if (coach.getName().toLowerCase().contains(s.toLowerCase().trim())) {
                filteredList.add(coach);
            }
        }
        listCoachManagementAdapter.filterList(filteredList);
    }

    protected void getWidget() {
        context = this;
        recyclerViewCoachManagement = (RecyclerView) findViewById(R.id.coachManagementRecyclerView);
        imgButtonAddCoach = (ImageButton) findViewById(R.id.addCoachButton);
        edtCoachManagementSearch = (EditText) findViewById(R.id.coachManagementSearchEditText);
        toolbarTitleCoachManagement = (TextView) findViewById(R.id.title);
        toolbarTitleCoachManagement.setText(R.string.coach_management);

        imgBackButton = (ImageButton) findViewById(R.id.backBtn);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgButtonAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTeam.class);
                startActivity(intent);
            }
        });
        
        listCoachManagementItem = new ArrayList<Coach>();
        recyclerViewCoachManagement.setLayoutManager(new LinearLayoutManager(this));
        listCoachManagementAdapter = new ListCoachManagementAdapter(listCoachManagementItem,CoachManagement.this, CoachManagement.this);
        recyclerViewCoachManagement.setAdapter(listCoachManagementAdapter);

        edtCoachManagementSearch.addTextChangedListener(new TextWatcher() {
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

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}