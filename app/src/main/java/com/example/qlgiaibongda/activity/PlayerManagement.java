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
import com.example.qlgiaibongda.adapter.ListPlayerManagementAdapter;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class PlayerManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListPlayerManagementAdapter.onItemClickListener {

    private RecyclerView recyclerViewPlayerManagement;
    private ImageButton imgButtonAddPlayer;
    private EditText edtPlayerManagementSearch;
    ArrayList<Player> listPlayerManagementItem;
    private ListPlayerManagementAdapter listPlayerManagementAdapter;
    private TextView toolbarTitlePlayerManagement;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_management);

        getWidget();

    }



    protected void getWidget() {
        recyclerViewPlayerManagement = (RecyclerView) findViewById(R.id.playerManagementRecyclerView);
        imgButtonAddPlayer = (ImageButton) findViewById(R.id.addPlayerButton);
        edtPlayerManagementSearch = (EditText) findViewById(R.id.playerManagementSearchEditText);
        toolbarTitlePlayerManagement = (TextView) findViewById(R.id.title);
        toolbarTitlePlayerManagement.setText(R.string.player_management);

        listPlayerManagementItem = new ArrayList<Player>();
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());
        listPlayerManagementItem.add(new Player());

        recyclerViewPlayerManagement.setLayoutManager(new LinearLayoutManager(this));
        listPlayerManagementAdapter = new ListPlayerManagementAdapter(listPlayerManagementItem,PlayerManagement.this, PlayerManagement.this);
        recyclerViewPlayerManagement.setAdapter(listPlayerManagementAdapter);
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
