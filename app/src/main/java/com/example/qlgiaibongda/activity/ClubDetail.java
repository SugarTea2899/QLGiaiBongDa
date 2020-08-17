package com.example.qlgiaibongda.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.TabLayoutClubDetailAdapter;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qlgiaibongda.state.State.isLogined;

public class ClubDetail extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ImageView imvClub;
    private TextView tvNameClub;
    private TextView tvStadium;
    private TextView tvSponsor;
    private ImageButton imbSetting;
    private ImageButton imbBack;

    private String clubId;
    private static final int REQUEST_CLUB_DETAIL_CODE = 200;
    private Team teamInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);

        clubId = getIntent().getStringExtra("teamId");

        toolbar = (Toolbar) findViewById(R.id.toolBar2);
        tabLayout = (TabLayout) findViewById(R.id.menuOptions);
        tabLayout.addTab(tabLayout.newTab().setText("TRẬN ĐẤU"));
        tabLayout.addTab(tabLayout.newTab().setText("THÀNH VIÊN"));
        tabLayout.addTab(tabLayout.newTab().setText("THÔNG SỐ"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvNameClub = (TextView) findViewById(R.id.nameOfClub);
        tvStadium = (TextView) findViewById(R.id.stadiumClub);
        tvSponsor = (TextView) findViewById(R.id.sponsorOfClub);
        imvClub = (ImageView) findViewById(R.id.imageOfClub);
        imbSetting = (ImageButton) findViewById(R.id.settingBtnAppBar2);
        imbBack = (ImageButton) findViewById(R.id.backBtn);

        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (isLogined) {
            imbSetting.setVisibility(View.VISIBLE);
        }
        imbSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClubDetail.this, EditTeam.class);
                intent.putExtra("teamId", clubId);
                startActivityForResult(intent, REQUEST_CLUB_DETAIL_CODE);
            }
        });

        loadData();
    }

    private void loadData() {
        DataClient dataClient = APIUtils.getData();
        Call<Team> callBackTeamInfo = dataClient.getInfoTeam(clubId);
        callBackTeamInfo.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    teamInfo = (Team)response.body();
                    tvNameClub.setText(teamInfo.getName());
                    tvStadium.setText(teamInfo.getStadium());
                    tvSponsor.setText(teamInfo.getSponsor());
                    if (teamInfo.getLogo() == null || teamInfo.getLogo().equals("")) {
                        Picasso.get()
                                .load(R.drawable.no_image)
                                .into(imvClub);
                    }
                    else {
                        Picasso.get()
                                .load(teamInfo.getLogo())
                                .error(R.drawable.no_image)
                                .into(imvClub);
                    }

                    final TabLayoutClubDetailAdapter adapter = new TabLayoutClubDetailAdapter(getApplicationContext(),getSupportFragmentManager(), tabLayout.getTabCount(), teamInfo);
                    viewPager.setAdapter(adapter);

                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CLUB_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }
}