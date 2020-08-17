package com.example.qlgiaibongda.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.constant.Constant;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPlayer extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private Spinner spnPlayerType;
    private Spinner spnTeam;
    private ImageButton btnDatePicker;
    private ImageButton btnFolder;
    private ImageButton btnBack;
    private Button btnAdd;
    private TextView tvPlayerDOB;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;
    private EditText edtPlayerName;
    private EditText edtPlayerNationality;
    private EditText edtPlayerNumber;

    private ArrayList<Team> teamList;
    private int typeSelected = -1;
    private Team teamSelected = null;

    private String imagePath = null;
    private final int REQUEST_CODE_IMAGE = 99;
    public static final int STORAGE_PERMISSION_CODE = 1;
    private String playerId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        getWidget();
        tvTitle.setText("Chỉnh sửa cầu thủ");
        setSupportActionBar(toolbar);
        setTitle(null);
        checkPermission();
        initTypeSpinner();
        setInfo();
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        spnPlayerType = (Spinner) findViewById(R.id.spnTypePlayer);
        btnDatePicker = (ImageButton) findViewById(R.id.btnDatePicker);
        tvPlayerDOB = (TextView) findViewById(R.id.tvPlayerDOB);
        btnFolder = (ImageButton) findViewById(R.id.btnFolder);
        circleImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.cvPlayer);
        teamList = new ArrayList<>();
        spnTeam = (Spinner) findViewById(R.id.spnTeam);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtPlayerName = (EditText) findViewById(R.id.edtPlayerName);
        edtPlayerNationality = (EditText) findViewById(R.id.edtPlayerNationality);
        edtPlayerNumber = (EditText) findViewById(R.id.edtPlayerNumber);
        btnBack = (ImageButton) findViewById(R.id.backBtn);
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
                pickDate();
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updatePlayerToApi();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        spnPlayerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    switch (position){
                        case 1:
                            typeSelected = Constant.GOAL_KEEPER;
                            break;
                        case 2:
                            typeSelected = Constant.DEFENDER;
                            break;
                        case 3:
                            typeSelected = Constant.MIDFIELDER;
                            break;
                        case 4:
                            typeSelected = Constant.FORWARD;
                            break;
                        default:
                            typeSelected = -1;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    teamSelected = teamList.get(position - 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setInfo(){
        playerId = getIntent().getStringExtra("playerId");

        final ProgressDialog dialog = new ProgressDialog(EditPlayer.this);
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
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    edtPlayerName.setText(player.getName());
                    tvPlayerDOB.setText(simpleDateFormat.format(player.getDob()));
                    spnPlayerType.setSelection(player.getType() + 1);
                    typeSelected = player.getType();

                    edtPlayerNationality.setText(player.getNationality());
                    edtPlayerNumber.setText(String.valueOf(player.getNumber()));

                    if (player.getAvatar() == null || player.getAvatar().length() == 0){
                        Picasso.get().load(R.drawable.no_image).into(circleImageView);
                    }else{
                        Picasso.get().load(APIUtils.BASE_URL + player.getAvatar()).error(R.drawable.no_image).into(circleImageView);
                    }

                    Call<List<Team>> callback1 = dataClient.getTeamList();
                    callback1.enqueue(new Callback<List<Team>>() {
                        @Override
                        public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                            if (response.isSuccessful()){
                                teamList = (ArrayList<Team>) response.body();
                                String[] nameArr = new String[teamList.size() + 2];
                                nameArr[0] = "Chọn đội";
                                nameArr[1] = "Cầu thủ tự do";
                                int i = 2;
                                for (Team team: teamList){
                                    nameArr[i++] = team.getName();
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, nameArr){
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

                                spnTeam.setAdapter(adapter);

                                int index = searchTeam(player.getTeamId());
                                spnTeam.setSelection(index);
                                teamSelected = teamList.get(index - 2);
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getApplicationContext(), "Lỗi không tìm thấy danh sách đội", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_CANCELED);
                                finish();
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Team>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Lỗi không tìm thấy danh sách đội", Toast.LENGTH_SHORT).show();
                            setResult(Activity.RESULT_CANCELED);
                            finish();
                            dialog.dismiss();
                        }
                    });

                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                    finish();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Load dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK);
                finish();
                dialog.dismiss();
            }
        });
    }

    private int searchTeam(String id){
        for (int i = 0;i < teamList.size(); i++){
            if (teamList.get(i).getId().equals(id)){
                return i + 2;
            }
        }

        return 1;
    }

    private void updatePlayerToApi() throws ParseException {
        String playerName = edtPlayerName.getText().toString();
        String playerDOB = tvPlayerDOB.getText().toString();
        String playerNationality = edtPlayerNationality.getText().toString();
        String playerNumber = edtPlayerNumber.getText().toString();

        if (playerName.length() == 0 || playerDOB.length() == 0 || playerNationality.length() == 0 || playerNumber.length() == 0
                || typeSelected == -1 || teamSelected == null ){
            Toast.makeText(getApplicationContext(), "Thông tin rỗng", Toast.LENGTH_SHORT).show();
            return;
        }

        int number = Integer.parseInt(playerNumber);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd");
        playerDOB = apiFormat.format(format.parse(playerDOB));

        final ProgressDialog dialog = new ProgressDialog(EditPlayer.this);
        dialog.setTitle("Cập nhật cầu thủ.");
        dialog.setMessage("Xin chờ...");
        dialog.show();

        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callback = dataClient.updatePlayer(playerId ,playerName, playerDOB, typeSelected, playerNationality, teamSelected.getId(), number);
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String playerId = jsonObject.getString("playerId");

                        if (imagePath != null){
                            File file = new File(imagePath);
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);

                            Call<ResponseBody> callback1 = dataClient.uploadPlayerAvatar(playerId, body);
                            callback1.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Chỉnh sửa cầu thủ thành công.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        setResult(Activity.RESULT_OK);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Chỉnh sửa cầu thủ thất bại", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Chỉnh cầu thủ thất bại", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            });
                        }else {
                            Toast.makeText(getApplicationContext(), "Chỉnh sửa cầu thủ thành công.", Toast.LENGTH_SHORT).show();
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
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        String message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi thêm cầu thủ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            imagePath = getRealPathFromURI(uri);
            circleImageView.setImageURI(uri);
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

    private void pickDate(){
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvPlayerDOB.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);

        datePickerDialog.show();
    }

    private void initTypeSpinner(){
        String[] playerTypes = getResources().getStringArray(R.array.player_type);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, playerTypes){
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

        spnPlayerType.setAdapter(adapter);
    }


    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(EditPlayer.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Quyền đã được cấp.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Ứng dụng cần quyền để tiếp tục.", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }
    }
}
