package com.example.qlgiaibongda.activity;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qlgiaibongda.activity.AddPlayer.STORAGE_PERMISSION_CODE;

public class AddTeam extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;

    private ImageButton btnFolder;
    private ImageButton btnBack;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;

    private EditText edtTeamName;
    private EditText edtDonors;
    private EditText edtStadium;
    private EditText edtShortName;

    private Button btnAdd;

    private final int REQUEST_CODE_IMAGE = 99;
    private String imagePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        getWidget();
        tvTitle.setText("Thêm đội bóng");
        setSupportActionBar(toolbar);
        setTitle(null);
        checkPermission();
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        btnFolder = (ImageButton) findViewById(R.id.btnFolder);
        circleImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.cvTeamLogo);
        edtTeamName = (EditText) findViewById(R.id.edtTeamName);
        edtDonors = (EditText) findViewById(R.id.edtDonors);
        edtStadium = (EditText) findViewById(R.id.edtStadium);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtShortName = (EditText) findViewById(R.id.edtShortName);
        btnBack = (ImageButton) findViewById(R.id.backBtn);
    }

    private void setEvent(){
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
                addTeam();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(AddTeam.this,
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
                finish();
            }
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

    private void addTeam(){
        String teamName = edtTeamName.getText().toString();
        String donors = edtDonors.getText().toString();
        String statidum = edtStadium.getText().toString();
        String shortName = edtShortName.getText().toString();

        if (teamName.length() == 0 || donors.length() == 0 || statidum.length() == 0 || shortName.length() == 0){
            Toast.makeText(getApplicationContext(), "Thông tin rỗng", Toast.LENGTH_SHORT).show();
            return;
        }


        final ProgressDialog dialog = new ProgressDialog(AddTeam.this);
        dialog.setTitle("Thêm đội bóng");
        dialog.setMessage("Xin chờ...");
        dialog.show();


        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callback = dataClient.addTeam(teamName, shortName, statidum, donors);
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
                                        Toast.makeText(getApplicationContext(), "Thêm đội thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Thêm đội thành công.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Thêm đội thất bại", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Thêm đội thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thêm đội thất bại", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
}
