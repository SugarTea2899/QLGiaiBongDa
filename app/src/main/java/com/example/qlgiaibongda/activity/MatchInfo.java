package com.example.qlgiaibongda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.TabLayoutMatchInfoAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchInfo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ImageButton imvAddGoal;
    private ImageButton imvAddSubstitute;
    private ImageButton imvAddCard;
    private ImageButton imvEndMatch;
    private ImageButton imbRemoveMatch;
    private ImageButton imbEditMatch;
    private ImageButton imbMenu;
    private ImageView imvHomeLogo;
    private ImageView imvGuestLogo;
    private TextView tvDateStart;
    private TextView tvResult;
    private TextView tvRound;
    private TextView tvNameHome;
    private TextView tvNameGuest;

    private String matchId = "5ee4b5f0b06109245439416c";
    private Match matchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        toolbar = (Toolbar) findViewById(R.id.toolBarMatchInfo);
        tabLayout = (TabLayout) findViewById(R.id.menuOptions);
        tabLayout.addTab(tabLayout.newTab().setText("DIỄN BIẾN"));
        tabLayout.addTab(tabLayout.newTab().setText("ĐỘI HÌNH"));
        tabLayout.addTab(tabLayout.newTab().setText("THÔNG SỐ"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvDateStart = (TextView) findViewById(R.id.dateOfMatch);
        tvResult = (TextView) findViewById(R.id.resultOfMatch);
        tvRound = (TextView) findViewById(R.id.roundOfMatch);
        tvNameHome = (TextView) findViewById(R.id.nameHomeClub);
        tvNameGuest = (TextView) findViewById(R.id.nameGuestClub);

        DataClient dataClient = APIUtils.getData();
        Call<Match> callBackMatchInfo = dataClient.getMatchInfo(matchId);
        callBackMatchInfo.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                if (response.isSuccessful()) {
                    matchInfo = (Match) response.body();

                    Date date = matchInfo.getDateStart();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    tvDateStart.setText(sdf.format(date));

                    if (matchInfo.getStateMatch() == 0) {
                        tvResult.setText("-");
                    }
                    else {
                        tvResult.setText(matchInfo.getHomeGoal().toString() + " - " + matchInfo.getGuestGoal().toString());
                    }
                    tvRound.setText("Vòng " + matchInfo.getRound().toString());
                    tvNameHome.setText(matchInfo.getHomeTeam());
                    tvNameGuest.setText(matchInfo.getGuestTeam());

                    final TabLayoutMatchInfoAdapter adapter = new TabLayoutMatchInfoAdapter(getApplicationContext(),getSupportFragmentManager(), tabLayout.getTabCount(), matchInfo);
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
                    Toast.makeText(getApplicationContext(), "Không tìm thấy", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin trận đấu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imvEndMatch = (ImageButton) findViewById(R.id.endMatch);
        imvEndMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertConfirmDialog();
            }
        });

        imvAddGoal = (ImageButton) findViewById(R.id.addGoal);
        imvAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAddGoalPopupDialog();
            }
        });

        imvAddSubstitute = (ImageButton) findViewById(R.id.addSubstitute);
        imvAddSubstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAddSubstitutePopupDialog();
            }
        });

        imvAddCard = (ImageButton) findViewById(R.id.addCard);
        imvAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAddCardPopupDialog();
            }
        });

        imbRemoveMatch = (ImageButton) findViewById(R.id.removeMatch);
        imbEditMatch = (ImageButton) findViewById(R.id.editMatch);

        imbMenu = (ImageButton) findViewById(R.id.menuButton);
        imbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imvAddCard.getVisibility() == View.GONE) {
                    imvAddCard.setVisibility(View.VISIBLE);
                    imvAddGoal.setVisibility(View.VISIBLE);
                    imvAddSubstitute.setVisibility(View.VISIBLE);
                    imvEndMatch.setVisibility(View.VISIBLE);
                    imbEditMatch.setVisibility(View.VISIBLE);
                    imbRemoveMatch.setVisibility(View.VISIBLE);
                }
                else {
                    imvAddCard.setVisibility(View.GONE);
                    imvAddGoal.setVisibility(View.GONE);
                    imvAddSubstitute.setVisibility(View.GONE);
                    imvEndMatch.setVisibility(View.GONE);
                    imbEditMatch.setVisibility(View.GONE);
                    imbRemoveMatch.setVisibility(View.GONE);
                }
            }
        });
    }

    private void AlertConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận kết thúc trận đấu ?");
        builder.setMessage("Trạng thái trận đấu đổi thành kết thúc sau khi xác nhận");
        builder.setCancelable(false);
        builder.setPositiveButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void DisplayAddGoalPopupDialog() {
        final Dialog dialog = new Dialog(MatchInfo.this);
        dialog.setContentView(R.layout.popup_add_goal);

        RadioButton isHomeTeam = (RadioButton) dialog.findViewById(R.id.homeTeam);
        RadioButton isGuestTeam = (RadioButton) dialog.findViewById(R.id.guestTeam);
        Spinner spnPlayer = (Spinner) dialog.findViewById(R.id.spnPlayer1);
        Spinner spnAssist = (Spinner) dialog.findViewById(R.id.spnPlayer2);
        Spinner spnType = (Spinner) dialog.findViewById(R.id.spnTypeGoal);

        List<String> test = new ArrayList<>();
        test.add("Cầu thủ ghi bàn");
        test.add("1");
        test.add("2");
        test.add("3");
        ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnPlayer.setAdapter(playerAdapter);

        List<String> test2 = new ArrayList<>();
        test2.add("Cầu thủ kiến tạo");
        test2.add("1");
        test2.add("2");
        test2.add("3");
        ArrayAdapter<String> assistAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test2){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnAssist.setAdapter(assistAdapter);

        List<String> typeGoal = new ArrayList<>();
        typeGoal.add("Loại bàn thắng");
        typeGoal.add("Bàn thắng bình thường");
        typeGoal.add("Bàn thắng phạt đền");
        typeGoal.add("Bàn phản lưới nhà");
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeGoal){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnType.setAdapter(typeAdapter);

        dialog.show();
    }

    private void DisplayAddSubstitutePopupDialog() {
        final Dialog dialog = new Dialog(MatchInfo.this);
        dialog.setContentView(R.layout.popup_add_substitute);

        RadioButton isHomeTeam = (RadioButton) dialog.findViewById(R.id.homeTeam);
        RadioButton isGuestTeam = (RadioButton) dialog.findViewById(R.id.guestTeam);
        Spinner spnPlayerIn = (Spinner) dialog.findViewById(R.id.spnPlayerIn);
        Spinner spnPlayerOut = (Spinner) dialog.findViewById(R.id.spnPlayerOut);

        List<String> test = new ArrayList<>();
        test.add("Cầu thủ ra sân");
        test.add("1");
        test.add("2");
        test.add("3");
        ArrayAdapter<String> playerInAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnPlayerIn.setAdapter(playerInAdapter);

        List<String> test2 = new ArrayList<>();
        test2.add("Cầu thủ vào sân");
        test2.add("1");
        test2.add("2");
        test2.add("3");
        ArrayAdapter<String> playerOutAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test2){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnPlayerOut.setAdapter(playerOutAdapter);

        dialog.show();
    }

    private void DisplayAddCardPopupDialog() {
        final Dialog dialog = new Dialog(MatchInfo.this);
        dialog.setContentView(R.layout.popup_add_card);

        RadioButton isHomeTeam = (RadioButton) dialog.findViewById(R.id.homeTeam);
        RadioButton isGuestTeam = (RadioButton) dialog.findViewById(R.id.guestTeam);
        Spinner spnPlayer = (Spinner) dialog.findViewById(R.id.spnPlayer);
        Spinner spnType = (Spinner) dialog.findViewById(R.id.spnType);

        List<String> test = new ArrayList<>();
        test.add("Cầu thủ ra sân");
        test.add("1");
        test.add("2");
        test.add("3");
        ArrayAdapter<String> playerInAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnPlayer.setAdapter(playerInAdapter);

        List<String> test2 = new ArrayList<>();
        test2.add("Cầu thủ vào sân");
        test2.add("1");
        test2.add("2");
        test2.add("3");
        ArrayAdapter<String> playerOutAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test2){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        spnType.setAdapter(playerOutAdapter);

        dialog.show();
    }
}
