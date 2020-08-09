package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;

public class RefereeDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referee_detail);
        getWidget();
        tvTitle.setText("Thông tin trọng tài");
        setSupportActionBar(toolbar);
        setTitle(null);
        setEvent();
    }


    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
    }

    private void setEvent(){

    }
}
