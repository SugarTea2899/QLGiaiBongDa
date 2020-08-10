package com.example.qlgiaibongda.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;

public class AddTeam extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;

    private ImageButton btnFolder;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;

    private final int REQUEST_CODE_IMAGE = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        getWidget();
        tvTitle.setText("Thêm đội bóng");
        setSupportActionBar(toolbar);
        setTitle(null);
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        btnFolder = (ImageButton) findViewById(R.id.btnFolder);
        circleImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.cvTeamLogo);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
        }
    }
}
