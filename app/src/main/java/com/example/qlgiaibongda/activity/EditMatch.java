package com.example.qlgiaibongda.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Referee;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMatch extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private ImageButton btnDatePicker;
    private TextView tvDateStart;

    private Spinner spnHomeTeam;
    private Spinner spnGuestTeam;
    private Spinner spnReferee;

    private EditText edtRound;
    private EditText edtStadium;

    private ArrayList<Team> teamList;
    private ArrayList<Referee> refereeList;

    private Button btnAdd;
    private ImageButton btnBack;

    private int selectedHomeTeam = -1;
    private int selectedGuestTeam = -1;
    private int selectedReferee = -1;

    private String matchId;
    private Match match;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        getWidget();
        tvTitle.setText("Chỉnh sửa trận đấu");
        setSupportActionBar(toolbar);
        setTitle(null);
        fillInfo();
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        btnDatePicker = (ImageButton)findViewById(R.id.btnDatePicker);
        tvDateStart = (TextView) findViewById(R.id.tvDateStart);
        spnHomeTeam = (Spinner) findViewById(R.id.spnHomeTeam);
        spnGuestTeam = (Spinner) findViewById(R.id.spnGuestTeam);
        spnReferee = (Spinner) findViewById(R.id.spnReferee);
        teamList = new ArrayList<>();
        refereeList = new ArrayList<>();
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtStadium = (EditText) findViewById(R.id.edtStadium);
        edtRound = (EditText) findViewById(R.id.edtRound);
        btnBack = (ImageButton) findViewById(R.id.backBtn);
    }

    private void pickDateTime(){
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                TimePickerDialog timePickerDialog = new TimePickerDialog(EditMatch.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tvDateStart.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },0, 0, true);

                timePickerDialog.show();
            }
        }, year,month,date);

        datePickerDialog.show();

    }

    private void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
            }
        });

        spnHomeTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    selectedHomeTeam = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnGuestTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    selectedGuestTeam = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnReferee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    selectedReferee = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editMatch();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void fillInfo(){
        matchId = getIntent().getStringExtra("matchId");
        final ProgressDialog dialog = new ProgressDialog(EditMatch.this);
        dialog.setTitle("Load dữ liệu");
        dialog.setMessage("Xin chờ...");
        dialog.show();
        DataClient dataClient = APIUtils.getData();
        Call<Match> callback = dataClient.getMatchInfo(matchId);
        callback.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                if (response.isSuccessful()){
                    match = (Match) response.body();
                    edtRound.setText(String.valueOf(match.getRound()));
                    edtStadium.setText(match.getStadium());

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    tvDateStart.setText(simpleDateFormat.format(match.getDateStart()));

                    initSpnTeam(dataClient, dialog);
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {

            }
        });

    }

    private void initSpnTeam(DataClient dataClient, ProgressDialog dialog){
        Call<List<Team>> callback = dataClient.getTeamList();
        callback.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if (response.isSuccessful()){
                    teamList = (ArrayList<Team>) response.body();
                    String[] arrHome = new String[teamList.size() + 1];
                    String[] arrGuest = new String[teamList.size() + 1];

                    arrHome[0] = "Chọn đội nhà";
                    arrGuest[0] = "Chọn đội khách";

                    int i = 1;
                    for (Team team: teamList){
                        arrHome[i] = team.getName();
                        arrGuest[i] = team.getName();
                        i++;
                    }

                    ArrayAdapter<String> adapterHome = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrHome){
                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0)
                                return false;
                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view =  super.getDropDownView(position, convertView, parent);
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


                    ArrayAdapter<String> adapterGuest = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrGuest){
                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0)
                                return false;
                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view =  super.getDropDownView(position, convertView, parent);
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

                    spnGuestTeam.setAdapter(adapterGuest);
                    spnHomeTeam.setAdapter(adapterHome);


                    for (int j = 0; j < teamList.size(); j++){
                        if (match.getHomeTeam().equals(teamList.get(j).getName())){
                            selectedHomeTeam = j;
                            spnHomeTeam.setSelection(j + 1);
                        }

                        if (match.getGuestTeam().equals(teamList.get(j).getName())){
                            selectedGuestTeam = j;
                            spnGuestTeam.setSelection(j + 1);
                        }
                    }

                    initSpnReferee(dataClient, dialog);

                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initSpnReferee(DataClient dataClient, ProgressDialog dialog){
        Call<List<Referee>> callback = dataClient.getRefereeList();
        callback.enqueue(new Callback<List<Referee>>() {
            @Override
            public void onResponse(Call<List<Referee>> call, Response<List<Referee>> response) {
                if (response.isSuccessful()){
                    refereeList = (ArrayList<Referee>) response.body();

                    String[] arr = new String[refereeList.size() + 1];
                    int i = 0;
                    arr[i++] = "Chọn trọng tài";

                    for (Referee referee: refereeList){
                        arr[i++] = referee.getName();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arr){
                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0)
                                return false;
                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view =  super.getDropDownView(position, convertView, parent);
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

                    spnReferee.setAdapter(adapter);

                    for (int j = 0; j < refereeList.size(); j++){
                        if (match.getRefereeId().equals(refereeList.get(j).getId())){
                            selectedReferee = j;
                            spnReferee.setSelection(j + 1);
                            break;
                        }
                    }

                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Referee>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void editMatch() throws ParseException {
        String stadium = edtStadium.getText().toString();
        String roundStr = edtRound.getText().toString();
        String dateStart = tvDateStart.getText().toString();

        if (roundStr.length() == 0 || stadium.length() == 0 || dateStart.length() == 0 || selectedGuestTeam == -1
                || selectedHomeTeam == -1 || selectedReferee == -1){
            Toast.makeText(getApplicationContext(), "Thông tin rỗng", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedHomeTeam == selectedGuestTeam){
            Toast.makeText(getApplicationContext(), "Đội nhà và đội khác trùng nhau", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(EditMatch.this);
        dialog.setTitle("Thêm trận đấu");
        dialog.setMessage("Xin chờ...");
        dialog.show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callback = dataClient.updateMatch(matchId ,teamList.get(selectedHomeTeam).getName(),
                teamList.get(selectedGuestTeam).getName(),simpleDateFormat.parse(dateStart).getTime(), stadium,
                refereeList.get(selectedReferee).getId(), Integer.parseInt(roundStr));

        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Chỉnh sửa trận đấu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    setResult(Activity.RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Chỉnh sửa trận đấu thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Chỉnh sửa trận đấu thất bại", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }
        });
    }
}
