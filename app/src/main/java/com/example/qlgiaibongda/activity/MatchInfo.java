package com.example.qlgiaibongda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.TabLayoutMatchInfoAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qlgiaibongda.state.State.isLogined;

public class MatchInfo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
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
    private TextView tvTitle;
    private ImageButton imbBack;

    private String matchId;
    private Match matchInfo;
    private static final int REQUEST_EDIT_MATCH = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        matchId = getIntent().getStringExtra("matchId");

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
        imvHomeLogo = (ImageView) findViewById(R.id.logoHomeClub);
        imvGuestLogo = (ImageView) findViewById(R.id.logoGuestClub);
        tvTitle = (TextView) findViewById(R.id.title);
        imbBack = (ImageButton) findViewById(R.id.backBtn);

        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("Thông tin trận đấu");
        if (isLogined) {
            imbMenu.setVisibility(View.VISIBLE);
        }

        loadData();

        imvEndMatch = (ImageButton) findViewById(R.id.endMatch);
        imvEndMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertConfirmRemoveDialog(matchId, 2);
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
        imbEditMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchInfo.this, EditMatch.class);
                intent.putExtra("matchId", matchId);
                startActivityForResult(intent, REQUEST_EDIT_MATCH);
            }
        });

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

    private void loadData() {
        DataClient dataClient = APIUtils.getData();
        Call<Match> callBackMatchInfo = dataClient.getMatchInfo(matchId);
        callBackMatchInfo.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                if (response.isSuccessful()) {
                    matchInfo = (Match) response.body();

                    Date date = matchInfo.getDateStart();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd/MM/yyyy");
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
                    if (matchInfo.getLogoHome() == null || matchInfo.getLogoHome().equals("")) {
                        Picasso.get()
                                .load(R.drawable.no_image)
                                .into(imvHomeLogo);
                    }
                    else {
                        Picasso.get()
                                .load(matchInfo.getLogoHome())
                                .error(R.drawable.no_image)
                                .into(imvHomeLogo);
                    }
                    if (matchInfo.getLogoGuest() == null || matchInfo.getLogoGuest().equals("")) {
                        Picasso.get()
                                .load(R.drawable.no_image)
                                .into(imvGuestLogo);
                    }
                    else {
                        Picasso.get()
                                .load(matchInfo.getLogoGuest())
                                .error(R.drawable.no_image)
                                .into(imvGuestLogo);
                    }

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_MATCH && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }

    private void setSpinner(Spinner spnPlayer, ArrayList<String> namePlayer) {
        ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, namePlayer){
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

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(16);
                tv.setPadding(20,0,0,0);
                return view;
            }
        };
        spnPlayer.setAdapter(playerAdapter);
    }

    private void AlertConfirmRemoveDialog(String matchId, Integer state) {
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
                DataClient dataClient = APIUtils.getData();
                Call<ResponseBody> callEndMatch = dataClient.endMatch(matchId, state);
                callEndMatch.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
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
        Spinner spnType = (Spinner) dialog.findViewById(R.id.spnTypeGoal);
        EditText edtMinute = (EditText) dialog.findViewById(R.id.edtMinute);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnConfirm);

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

        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callBackByName = dataClient.getPlayerByTeamName(matchId);
        callBackByName.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String result = null;
                    ArrayList<Player> listHome = null;
                    ArrayList<Player> listGuest = null;
                    try {
                        result = (String) response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        listHome = new Gson().fromJson(jsonObject.getString("listHome"), new TypeToken<ArrayList<Player>>(){}.getType());
                        listGuest = new Gson().fromJson(jsonObject.getString("listGuest"), new TypeToken<ArrayList<Player>>(){}.getType());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ArrayList<String> namePlayerHome = new ArrayList<>();
                    for (Player player : listHome) {
                        namePlayerHome.add(player.getName());
                    }
                    ArrayList<String> namePlayGuest = new ArrayList<>();
                    for (Player player : listGuest) {
                        namePlayGuest.add((player.getName()));
                    }

                    isHomeTeam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setSpinner(spnPlayer, namePlayerHome);
                        }
                    });

                    isGuestTeam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setSpinner(spnPlayer, namePlayGuest);
                        }
                    });

                    ArrayList<Player> finalListHome = listHome;
                    ArrayList<Player> finalListGuest = listGuest;
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataClient dataClient = APIUtils.getData();
                            Call<ResponseBody> call = dataClient.addMatchDetail(matchId, spnType.getSelectedItemPosition() - 1, Integer.parseInt(edtMinute.getText().toString()),
                                    isHomeTeam.isSelected(), isHomeTeam.isSelected() ? finalListHome.get(spnPlayer.getSelectedItemPosition()).getId() : finalListGuest.get(spnPlayer.getSelectedItemPosition()).getId(), null, null);
                            call.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                        loadData();
                                        dialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "onFailure add goal", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    dialog.show();
                }
                else {
                    Log.d("error", "onFaiure callBackByName");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("error", "onFaiure callBackByName");
            }
        });
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
        EditText edtMinute = (EditText) dialog.findViewById(R.id.edtMinute);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnConfirm);

        List<String> type = new ArrayList<>();
        type.add("Loại thẻ");
        type.add("Thẻ vàng");
        type.add("Thẻ đỏ");
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, type){
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

        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callBackByName = dataClient.getPlayerByTeamName(matchId);
        callBackByName.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String result = null;
                    ArrayList<Player> listHome = null;
                    ArrayList<Player> listGuest = null;
                    try {
                        result = (String) response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        listHome = new Gson().fromJson(jsonObject.getString("listHome"), new TypeToken<ArrayList<Player>>(){}.getType());
                        listGuest = new Gson().fromJson(jsonObject.getString("listGuest"), new TypeToken<ArrayList<Player>>(){}.getType());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ArrayList<String> namePlayerHome = new ArrayList<>();
                    for (Player player : listHome) {
                        namePlayerHome.add(player.getName());
                    }
                    ArrayList<String> namePlayGuest = new ArrayList<>();
                    for (Player player : listGuest) {
                        namePlayGuest.add((player.getName()));
                    }

                    isHomeTeam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setSpinner(spnPlayer, namePlayerHome);
                        }
                    });

                    isGuestTeam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setSpinner(spnPlayer, namePlayGuest);
                        }
                    });

                    ArrayList<Player> finalListHome = listHome;
                    ArrayList<Player> finalListGuest = listGuest;
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataClient dataClient = APIUtils.getData();
                            Call<ResponseBody> call = dataClient.addMatchDetail(matchId, spnType.getSelectedItemPosition() + 3, Integer.parseInt(edtMinute.getText().toString()),
                                    isHomeTeam.isSelected(), isHomeTeam.isSelected() ? finalListHome.get(spnPlayer.getSelectedItemPosition()).getId() : finalListGuest.get(spnPlayer.getSelectedItemPosition()).getId(), null, null);
                            call.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                        loadData();
                                        dialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "onFailure add goal", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    dialog.show();
                }
                else {
                    Log.d("error", "onFaiure callBackByName");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("error", "onFaiure callBackByName");
            }
        });
    }
}