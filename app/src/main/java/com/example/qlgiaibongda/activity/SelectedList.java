package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.SelectPlayerAdapter;
import com.example.qlgiaibongda.constant.Constant;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class SelectedList extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitle;
    private ImageView imvTeamLogo;
    private Button btnSelect;
    private RecyclerView recyclerView;
    private ArrayList<Player> listPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list);
        getWidget();
        tvTitle.setText("Chọn đội hình ra sân");
        setSupportActionBar(toolbar);
        setTitle(null);
        initRcv();
        setEvent();
    }

    private void initRcv(){
        for (int i = 1; i <= 10; i++){
            Player player = new Player();
            player.setName("Ronaldo" + i);
            player.setNumber(i);
            player.setType(Constant.FORWARD);
            player.setAvatar("https://i.imgur.com/DvpvklR.png");
            listPlayer.add(player);
        }

        SelectPlayerAdapter adapter = new SelectPlayerAdapter(listPlayer, SelectedList.this);
        recyclerView.setAdapter(adapter);
    }
    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        imvTeamLogo = (ImageView) findViewById(R.id.imvTeamLogo);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        recyclerView = (RecyclerView) findViewById(R.id.rvPlayerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listPlayer = new ArrayList<>();
    }

    private void setEvent(){

    }
}
