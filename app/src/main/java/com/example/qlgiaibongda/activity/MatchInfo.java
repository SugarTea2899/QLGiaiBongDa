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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.TabLayoutMatchInfoAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MatchInfo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ImageButton imvAddGoal;
    private ImageButton imvAddSubstitute;
    private ImageButton imvAddCard;
    private ImageButton imvEndMatch;

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

        final TabLayoutMatchInfoAdapter adapter = new TabLayoutMatchInfoAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
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
