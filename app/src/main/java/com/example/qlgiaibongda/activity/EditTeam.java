package com.example.qlgiaibongda.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Coach;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTeam extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;

    private ImageButton btnFolder;
    private ImageButton btnBack;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;

    private EditText edtTeamName;
    private EditText edtShortName;
    private EditText edtDonnors;
    private EditText edtStadium;

    private Spinner spnCaptian;
    private Spinner spnCoach;

    private Button btnEdit;

    private String teamId;
    private Team team;
    private ArrayList<Player> playerList;
    private ArrayList<Coach> coachList;
    private int selectedCaptain = -1;
    private int selectedCoach = -1;
    private String imagePath = null;

    private final int REQUEST_CODE_IMAGE = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        getWidget();
        tvTitle.setText("Chỉnh sửa đội bóng");
        setSupportActionBar(toolbar);
        setTitle(null);
        setInfo();
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        btnFolder = (ImageButton) findViewById(R.id.btnFolder);
        circleImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.cvTeamLogo);
        edtDonnors = (EditText) findViewById(R.id.edtDonors);
        edtStadium = (EditText) findViewById(R.id.edtStadium);
        edtTeamName = (EditText) findViewById(R.id.edtTeamName);
        edtShortName = (EditText) findViewById(R.id.edtShortName);
        spnCaptian = (Spinner) findViewById(R.id.spnCaptain);
        spnCoach = (Spinner) findViewById(R.id.spnCoach);
        playerList = new ArrayList<>();
        coachList = new ArrayList<>();
        btnEdit = (Button) findViewById(R.id.btnEditTeam);
        btnBack = (ImageButton) findViewById(R.id.backBtn);
    }

    private void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        spnCaptian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    selectedCaptain = position - 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    selectedCoach = position - 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    editTeam();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
            imagePath = getRealPathFromURI(uri);
        }
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    private void initSpnCaptain(DataClient dataClient, ProgressDialog dialog){
        Call<List<Player>> callback = dataClient.getListPlayerTeam(teamId);
        callback.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()){
                    playerList = (ArrayList<Player>) response.body();
                    String[] arr = new String[playerList.size() + 1];
                    int i = 0;
                    arr[i++] = "Chọn đội trưởng";

                    for (Player player: playerList){
                        arr[i++] = player.getName();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.support_simple_spinner_dropdown_item, arr){

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

                    spnCaptian.setAdapter(adapter);

                    if (team.getCaptainId() != null){

                        for (int j = 0; j < playerList.size(); j++){
                            if (playerList.get(j).getId().equals(team.getCaptainId())){
                                spnCaptian.setSelection(j + 1);
                                selectedCaptain = j;
                            }
                        }
                    }

                    initSpnCoach(dataClient, dialog);
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_CANCELED);
                    dialog.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
                dialog.dismiss();
                finish();
            }
        });
    }

    private void initSpnCoach(DataClient dataClient, ProgressDialog dialog){
        Call<List<Coach>> callback = dataClient.getCoachList();
        callback.enqueue(new Callback<List<Coach>>() {
            @Override
            public void onResponse(Call<List<Coach>> call, Response<List<Coach>> response) {
                if (response.isSuccessful()){
                    coachList = (ArrayList<Coach>) response.body();
                    String[] arr = new String[coachList.size() + 1];
                    int i = 0;
                    arr[i++] = "Chọn Huấn Luyện Viên";

                    for (Coach coach: coachList){
                        arr[i++] = coach.getName();
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

                    spnCoach.setAdapter(adapter);

                    if (team.getCoachId() != null){
                        for (int j = 0; j < coachList.size(); j++){
                            if (team.getCoachId().equals(coachList.get(j).getId())){
                                selectedCoach = j;
                                spnCoach.setSelection(selectedCoach + 1);
                            }
                        }
                    }

                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Coach>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setInfo(){
        teamId = getIntent().getStringExtra("teamId");
        final ProgressDialog dialog = new ProgressDialog(EditTeam.this);
        dialog.setTitle("Load dữ liệu");
        dialog.setMessage("Xin chờ...");
        dialog.show();

        DataClient dataClient = APIUtils.getData();
        Call<Team> callback = dataClient.getInfoTeam(teamId);
        callback.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()){
                    team = (Team) response.body();
                    edtDonnors.setText(team.getSponsor());
                    edtShortName.setText(team.getShortName());
                    edtStadium.setText(team.getStadium());
                    edtTeamName.setText(team.getName());

                    if (team.getLogo() != null && team.getLogo().length() > 0){
                        Picasso.get().load(team.getLogo()).error(R.drawable.no_image).into(circleImageView);
                    }else{
                        Picasso.get().load(R.drawable.no_image).into(circleImageView);
                    }

                    initSpnCaptain(dataClient, dialog);
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void editTeam(){
        String teamName = edtTeamName.getText().toString();
        String donors = edtDonnors.getText().toString();
        String statidum = edtStadium.getText().toString();
        String shortName = edtShortName.getText().toString();

        if (teamName.length() == 0 || donors.length() == 0 || statidum.length() == 0 || shortName.length() == 0
            || selectedCaptain == -1 || selectedCoach == -1){
            Toast.makeText(getApplicationContext(), "Thông tin rỗng", Toast.LENGTH_SHORT).show();
            return;
        }


        final ProgressDialog dialog = new ProgressDialog(EditTeam.this);
        dialog.setTitle("Cập nhật đội bóng");
        dialog.setMessage("Xin chờ...");
        dialog.show();


        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callback = dataClient.updateTeam(teamId ,teamName, shortName, statidum, donors, coachList.get(selectedCoach).getId(),
                                                            playerList.get(selectedCaptain).getId());
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String teamId = jsonObject.getString("teamId");

                        if (imagePath != null){
                            File file = new File(imagePath);
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            MultipartBody.Part body = MultipartBody.Part.createFormData("logo", file.getName(), requestBody);

                            Call<ResponseBody> callback1 = dataClient.uploadTeamLogo(teamId, body);
                            callback1.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        setResult(Activity.RESULT_OK);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thất bại.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thất bại", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thành công.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Chỉnh sửa đội thất bại", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }
        });


    }

}
