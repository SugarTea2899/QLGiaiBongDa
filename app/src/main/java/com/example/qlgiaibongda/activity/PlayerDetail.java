package com.example.qlgiaibongda.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.example.qlgiaibongda.state.State;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private TextView tvPlayerNumber;
    private TextView tvPlayerName;
    private TextView tvTeam;
    private TextView tvDOB;
    private TextView tvNationality;
    private TextView tvGoal;
    private TextView tvAssist;
    private TextView tvYellowCard;
    private TextView tvRedCard;

    private ImageView imvTeamLogo;
    private CircleImageView circleImageView;

    private ImageButton btnMenu;
    private ImageButton btnRemove;
    private ImageButton btnEdit;
    private ImageButton btnSetCaptain;
    private ImageButton btnBack;


    private boolean menuOn = false;


    private String playerId = "5f38d13cf79803402ca4d281";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        getWidget();
        tvTitle.setText("Thông tin cầu thủ");
        setSupportActionBar(toolbar);
        setTitle(null);
        checkState();
        fillInfo();
        setEvent();
    }


    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        tvPlayerNumber = (TextView) findViewById(R.id.tvPlayerNumber);
        tvPlayerName = (TextView) findViewById(R.id.tvPlayerName);
        tvDOB = (TextView) findViewById(R.id.tvDOB);
        tvNationality = (TextView) findViewById(R.id.tvNationality);
        tvGoal = (TextView) findViewById(R.id.tvGoal);
        tvYellowCard = (TextView) findViewById(R.id.tvYellowCard);
        tvRedCard = (TextView) findViewById(R.id.tvRedCard);
        tvTeam = (TextView) findViewById(R.id.tvTeam);
        tvAssist = (TextView) findViewById(R.id.tvAssist);
        imvTeamLogo = (ImageView) findViewById(R.id.ivTeamLogo);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnRemove = (ImageButton) findViewById(R.id.btnRemovePlayer);
        btnEdit = (ImageButton) findViewById(R.id.btnEditPlayer);
        btnSetCaptain = (ImageButton) findViewById(R.id.btnSetCaptain);
        circleImageView = (CircleImageView) findViewById(R.id.cvPlayer);
        btnBack = (ImageButton) findViewById(R.id.backBtn);
    }

    private void fillInfo(){
        final ProgressDialog dialog = new ProgressDialog(PlayerDetail.this);
        dialog.setTitle("Load dữ liệu");
        dialog.setMessage("Xin chờ...");
        dialog.show();

        DataClient dataClient = APIUtils.getData();
        Call<Player> callback = dataClient.getPlayerInfo(playerId);
        callback.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()){
                    Player player = (Player) response.body();

                    tvPlayerName.setText(player.getName());
                    tvPlayerNumber.setText(String.valueOf(player.getNumber()));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    tvDOB.setText(simpleDateFormat.format(player.getDob()));
                    tvNationality.setText(player.getNationality());
                    tvGoal.setText(String.valueOf(player.getTotalGoal()));
                    tvAssist.setText(String.valueOf(player.getTotalAssist()));
                    tvYellowCard.setText(String.valueOf(player.getTotalYellowCard()));
                    tvRedCard.setText(String.valueOf(player.getTotalRedCard()));

                    if (player.getAvatar() == null || player.getAvatar().length() == 0){
                        Picasso.get().load(R.drawable.no_avatar).into(circleImageView);
                    }else{
                        Picasso.get().load(player.getAvatar()).error(R.drawable.no_avatar).into(circleImageView);
                    }

                    if (player.getTeamId() != null){
                        Call<Team> callback1 = dataClient.getInfoTeam(player.getTeamId());
                        callback1.enqueue(new Callback<Team>() {
                            @Override
                            public void onResponse(Call<Team> call, Response<Team> response) {
                                if (response.isSuccessful()){
                                    Team team = (Team) response.body();
                                    tvTeam.setText(team.getName());
                                    Picasso.get().load(team.getLogo()).error(R.drawable.no_image).into(imvTeamLogo);
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Lỗi load dữ liệu", Toast.LENGTH_SHORT).show();
                                    finish();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Team> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Lỗi load dữ liệu", Toast.LENGTH_SHORT).show();
                                finish();
                                dialog.dismiss();
                            }
                        });
                    }else{
                        Picasso.get().load(R.drawable.no_image).into(imvTeamLogo);
                        tvTeam.setText("Cầu thủ tự do");
                        dialog.dismiss();
                    }


                }else{
                    Toast.makeText(getApplicationContext(), "Lỗi load dữ liệu", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi load dữ liệu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void setEvent(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOn){
                    btnEdit.setVisibility(View.GONE);
                    btnRemove.setVisibility(View.GONE);
                    btnSetCaptain.setVisibility(View.GONE);
                }else{
                    btnEdit.setVisibility(View.VISIBLE);
                    btnRemove.setVisibility(View.VISIBLE);
                    btnSetCaptain.setVisibility(View.VISIBLE);
                }

                menuOn = !menuOn;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkState(){
        if (State.isLogined){
            btnMenu.setVisibility(View.VISIBLE);
        }else{
            btnMenu.setVisibility(View.GONE);
        }
    }
}
