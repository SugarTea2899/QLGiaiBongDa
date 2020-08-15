package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.MatchProgressAdapter;
import com.example.qlgiaibongda.model.MatchStatDetails;

import java.util.ArrayList;
import java.util.List;

public class MatchProgressFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private MatchProgressAdapter matchProgressAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_match_progress, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        setEvent();
    }

    private void getWidget() {
        List<MatchStatDetails> list = new ArrayList<>();
        list.add(new MatchStatDetails(6));
        list.add(new MatchStatDetails(5));
        list.add(new MatchStatDetails(4));
        list.add(new MatchStatDetails(2));
        list.add(new MatchStatDetails(2));
        list.add(new MatchStatDetails(1));
        list.add(new MatchStatDetails(4));
        matchProgressAdapter = new MatchProgressAdapter(getContext(), list);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcvProgressMatch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(matchProgressAdapter);
    }

    private void setEvent() {

    }
}
