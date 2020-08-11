package com.example.qlgiaibongda.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlgiaibongda.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddMatch extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitle;
    private ImageButton btnDatePicker;
    private TextView tvDateStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        getWidget();
        tvTitle.setText("Thêm trận đấu");
        setSupportActionBar(toolbar);
        setTitle(null);
        setEvent();
    }

    private void getWidget(){
        toolbar = findViewById(R.id.toolBar1);
        tvTitle = (TextView) findViewById(R.id.title);
        btnDatePicker = (ImageButton)findViewById(R.id.btnDatePicker);
        tvDateStart = (TextView) findViewById(R.id.tvDateStart);
    }

    private void setEvent(){
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
            }
        });
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

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddMatch.this, new TimePickerDialog.OnTimeSetListener() {
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
}
