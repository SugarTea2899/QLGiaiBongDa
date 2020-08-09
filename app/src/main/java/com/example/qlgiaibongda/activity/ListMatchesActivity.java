package com.example.qlgiaibongda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMatchAdapter;
import com.example.qlgiaibongda.model.Match;

import java.util.ArrayList;
import java.util.Arrays;

public class ListMatchesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListMatchAdapter.onItemClickListener {
    private Spinner spinner;
    private RecyclerView rcvListMatches;
    private ListMatchAdapter listMatchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matches);

        getWidget();
        setEvents();
    }

    private void getWidget() {
        spinner = (Spinner) findViewById(R.id.roundSpinner);
        String[] values = {"Vòng đấu 1", "Vòng đấu 2", "Vòng đấu 3", "Vòng đấu 4", "Vòng đấu 5"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(values));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        rcvListMatches = (RecyclerView) findViewById(R.id.listMatches);
        ArrayList<Match> test = new ArrayList<>();
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        rcvListMatches.setLayoutManager(new LinearLayoutManager(this));
        listMatchAdapter = new ListMatchAdapter(test, ListMatchesActivity.this, ListMatchesActivity.this);
        rcvListMatches.setAdapter(listMatchAdapter);
    }

    private void setEvents() {
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(int i) {

    }
}
