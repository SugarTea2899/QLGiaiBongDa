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
import com.example.qlgiaibongda.adapter.ListRefereeManagementAdapter;
import com.example.qlgiaibongda.model.Referee;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.example.qlgiaibongda.state.State;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefereeManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListRefereeManagementAdapter.onItemClickListener {

    private Context context;
    private RecyclerView recyclerViewRefereeManagement;
    private ImageButton imgButtonAddReferee;
    private EditText edtRefereeManagementSearch;
    ArrayList<Referee> listRefereeManagementItem;
    private ListRefereeManagementAdapter listRefereeManagementAdapter;
    private AppBarLayout appBarLayoutRefereeManagement;
    private TextView toolbarTitleRefereeManagement;
    private DataClient dataClient = APIUtils.getData();
    ArrayList<Referee> filteredList = new ArrayList<>();

    private ImageButton imgBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referee_management);

        getWidget();
        if (!State.isLogined)
        {
            imgButtonAddReferee.setVisibility(View.INVISIBLE);
        }
        setEvent("");
    }

    private void setEvent(String searchText) {

        Call<ArrayList<Referee>> getListReferee = dataClient.getListSearchReferee(searchText);
        Context context = this;
        getListReferee.enqueue(new Callback<ArrayList<Referee>>() {
            @Override
            public void onResponse(Call<ArrayList<Referee>> call, Response<ArrayList<Referee>> response) {
                if (response.isSuccessful()) {
                    listRefereeManagementItem = (ArrayList<Referee>)response.body();
                    recyclerViewRefereeManagement.setLayoutManager(new LinearLayoutManager(context));
                    listRefereeManagementAdapter = new ListRefereeManagementAdapter(listRefereeManagementItem,RefereeManagement.this, RefereeManagement.this);
                    recyclerViewRefereeManagement.setAdapter(listRefereeManagementAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Referee>> call, Throwable t) {

            }
        });
    }

    private void filter(String s) {
        filteredList.clear();
        for(Referee referee : listRefereeManagementItem) {
            if (referee.getName().toLowerCase().contains(s.toLowerCase().trim())) {
                filteredList.add(referee);
            }
        }
        listRefereeManagementAdapter.filterList(filteredList);
    }


    protected void getWidget() {
        context = this;

        recyclerViewRefereeManagement = (RecyclerView) findViewById(R.id.refereeManagementRecyclerView);
        imgButtonAddReferee = (ImageButton) findViewById(R.id.addRefereeButton);
        edtRefereeManagementSearch = (EditText) findViewById(R.id.refereeManagementSearchEditText);
        appBarLayoutRefereeManagement = (AppBarLayout) findViewById(R.id.appbarRefereeManagement);
        toolbarTitleRefereeManagement = (TextView) findViewById(R.id.title);
        toolbarTitleRefereeManagement.setText(R.string.referee_management);
        imgBackButton = (ImageButton) findViewById(R.id.backBtn);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgButtonAddReferee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddReferee.class);
                startActivity(intent);
            }
        });


        listRefereeManagementItem = new ArrayList<Referee>();
        recyclerViewRefereeManagement.setLayoutManager(new LinearLayoutManager(this));
        listRefereeManagementAdapter = new ListRefereeManagementAdapter(listRefereeManagementItem,RefereeManagement.this, RefereeManagement.this);
        recyclerViewRefereeManagement.setAdapter(listRefereeManagementAdapter);


        edtRefereeManagementSearch.addTextChangedListener(new TextWatcher() {
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
        // Referee referee = filteredList.get(i);
        // Toast.makeText(this,referee.getName(),Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Referee referee = filteredList.get(i);
        // Toast.makeText(this,referee.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
