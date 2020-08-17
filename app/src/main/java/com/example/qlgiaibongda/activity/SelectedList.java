package com.example.qlgiaibongda.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.SelectPlayerAdapter;
import com.example.qlgiaibongda.constant.Constant;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedList extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitle;
    private ImageView imvTeamLogo;
    private Button btnSelect;
    private RecyclerView recyclerView;
    private ArrayList<Player> listPlayer;

    private String homeTeamId;
    private String guestTeamId;
    private String homeTeamLogo;
    private String guestTeamLogo;

    public static boolean isHomeScreen = true;
    private ArrayList<Player> homeTeamPlayers;
    private ArrayList<Player> guestTeamPlayer;

    public static HashMap<String, Player> homeSelectedPlayers = new HashMap<>();
    public static HashMap<String, Player> guestSelectedPlayers = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list);
        getWidget();
        tvTitle.setText("Chọn đội hình ra sân");
        setSupportActionBar(toolbar);
        setTitle(null);
        getPassedInfo();
        fillInfo();
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        imvTeamLogo = (ImageView) findViewById(R.id.imvTeamLogo);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        recyclerView = (RecyclerView) findViewById(R.id.rvPlayerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listPlayer = new ArrayList<>();
        homeTeamPlayers = new ArrayList<>();
        guestTeamPlayer = new ArrayList<>();
    }

    private void setEvent(){
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHomeScreen){
                    if (homeSelectedPlayers.size() < 11){
                        Toast.makeText(getApplicationContext(), "Đội hình chưa dủ 11 người", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    btnSelect.setText("Hoàn thành");
                    isHomeScreen = !isHomeScreen;
                    fillInfo();
                }else{
                    if (guestSelectedPlayers.size() < 11){
                        Toast.makeText(getApplicationContext(), "Đội hình chưa đủ 11 người", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    btnSelect.setText("Tiếp tục");
                }
            }
        });
    }




    private void getPassedInfo(){
        homeTeamId = getIntent().getStringExtra("homeTeamId");
        homeTeamLogo = getIntent().getStringExtra("homeTeamLogo");
        guestTeamId = getIntent().getStringExtra("guestTeamId");
        guestTeamLogo = getIntent().getStringExtra("guestTeamLogo");
    }
    private void fillInfo(){
        final ProgressDialog dialog = new ProgressDialog(SelectedList.this);
        dialog.setTitle("Load dữ liệu");
        dialog.setMessage("Xin chờ...");
        dialog.show();
        DataClient dataClient = APIUtils.getData();

        if (isHomeScreen){
            if (homeTeamPlayers.size() == 0){
                Call<List<Player>> callback = dataClient.getListPlayerTeam(homeTeamId);
                callback.enqueue(new Callback<List<Player>>() {
                    @Override
                    public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                        if (response.isSuccessful()){
                            homeTeamPlayers = (ArrayList<Player>) response.body();
                            if (homeTeamLogo == null || homeTeamLogo.length() == 0){
                                Picasso.get().load(R.drawable.no_image).into(imvTeamLogo);
                            }else{
                                Picasso.get().load(homeTeamLogo).error(R.drawable.no_image).into(imvTeamLogo);
                            }

                            SelectPlayerAdapter adapter = new SelectPlayerAdapter(homeTeamPlayers, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Player>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }else{
                SelectPlayerAdapter adapter = new SelectPlayerAdapter(homeTeamPlayers, getApplicationContext());
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }
        }else{
            if (guestTeamPlayer.size() == 0){
                Call<List<Player>> callback = dataClient.getListPlayerTeam(guestTeamId);
                callback.enqueue(new Callback<List<Player>>() {
                    @Override
                    public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                        if (response.isSuccessful()){
                            guestTeamPlayer = (ArrayList<Player>) response.body();
                            if (guestTeamLogo == null || guestTeamLogo.length() == 0){
                                Picasso.get().load(R.drawable.no_image).into(imvTeamLogo);
                            }else{
                                Picasso.get().load(guestTeamLogo).error(R.drawable.no_image).into(imvTeamLogo);
                            }

                            SelectPlayerAdapter adapter = new SelectPlayerAdapter(guestTeamPlayer, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Player>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }else{
                SelectPlayerAdapter adapter = new SelectPlayerAdapter(guestTeamPlayer, getApplicationContext());
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }
        }
    }
}
