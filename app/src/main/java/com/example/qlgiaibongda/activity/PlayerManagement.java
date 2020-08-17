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
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListPlayerManagementAdapter;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.example.qlgiaibongda.state.State;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListPlayerManagementAdapter.onItemClickListener {

    private Context context;
    private RecyclerView recyclerViewPlayerManagement;
    private ImageButton imgButtonAddPlayer;
    private EditText edtPlayerManagementSearch;
    ArrayList<Player> listPlayerManagementItem;
    private ListPlayerManagementAdapter listPlayerManagementAdapter;
    private TextView toolbarTitlePlayerManagement;

    private DataClient dataClient = APIUtils.getData();
    ArrayList<Player> filteredList = new ArrayList<>();


    private ImageButton imgBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_management);

        getWidget();
        if (!State.isLogined)
        {
            imgButtonAddPlayer.setVisibility(View.INVISIBLE);
        }
        setEvent("");

    }

    private void setEvent(String searchText) {
        Call<ArrayList<Player>> getListPlayer = dataClient.getListSearchPlayer(searchText);
        Context context = this;
        getListPlayer.enqueue(new Callback<ArrayList<Player>>() {
            @Override
            public void onResponse(Call<ArrayList<Player>> call, Response<ArrayList<Player>> response) {
                if (response.isSuccessful()) {
                    listPlayerManagementItem = (ArrayList<Player>) response.body();
                    recyclerViewPlayerManagement.setLayoutManager(new LinearLayoutManager(context));
                    listPlayerManagementAdapter = new ListPlayerManagementAdapter(listPlayerManagementItem, PlayerManagement.this, PlayerManagement.this);
                    recyclerViewPlayerManagement.setAdapter(listPlayerManagementAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Player>> call, Throwable t) {

            }
        });
    }

    private void filter(String s) {
        filteredList.clear();
        for (Player player : listPlayerManagementItem) {
            if (player.getName().toLowerCase().contains(s.toLowerCase().trim())) {
                filteredList.add(player);
            }
        }
        listPlayerManagementAdapter.filterList(filteredList);
    }

    protected void getWidget() {
        context = this;
        recyclerViewPlayerManagement = (RecyclerView) findViewById(R.id.playerManagementRecyclerView);
        imgButtonAddPlayer = (ImageButton) findViewById(R.id.addPlayerButton);
        edtPlayerManagementSearch = (EditText) findViewById(R.id.playerManagementSearchEditText);
        toolbarTitlePlayerManagement = (TextView) findViewById(R.id.title);
        toolbarTitlePlayerManagement.setText(R.string.player_management);

        imgBackButton = (ImageButton) findViewById(R.id.backBtn);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgButtonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddPlayer.class);
                startActivity(intent);
            }
        });


        listPlayerManagementItem = new ArrayList<Player>();
        recyclerViewPlayerManagement.setLayoutManager(new LinearLayoutManager(this));
        listPlayerManagementAdapter = new ListPlayerManagementAdapter(listPlayerManagementItem, PlayerManagement.this, PlayerManagement.this);
        recyclerViewPlayerManagement.setAdapter(listPlayerManagementAdapter);

        edtPlayerManagementSearch.addTextChangedListener(new TextWatcher() {
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
