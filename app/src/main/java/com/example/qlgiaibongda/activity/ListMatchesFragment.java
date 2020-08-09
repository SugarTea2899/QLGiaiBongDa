package com.example.qlgiaibongda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMatchAdapter;
import com.example.qlgiaibongda.model.Match;

import java.util.ArrayList;

public class ListMatchesFragment extends Fragment implements ListMatchAdapter.onItemClickListener {
    private View view;
    private RecyclerView rcvListMatch;
    private ListMatchAdapter listMatchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_matches, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        setEvent();
    }

    private void getWidget() {
        ArrayList<Match> test = new ArrayList<>();
        test.add(new Match());
        test.add(new Match());
        test.add(new Match());
        rcvListMatch = (RecyclerView) view.findViewById(R.id.rcvListMatchesFragment);
        rcvListMatch.setLayoutManager(new LinearLayoutManager(getContext()));
        listMatchAdapter = new ListMatchAdapter(test, getContext(), ListMatchesFragment.this);
        rcvListMatch.setAdapter(listMatchAdapter);
    }

    private void setEvent() {

    }

    @Override
    public void onItemClick(int i) {

    }
}
