package com.example.qlgiaibongda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMemberClubAdapter;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;

public class ListMemberClubFragment extends Fragment implements ListMemberClubAdapter.onItemClickListener {
    private RecyclerView rcvListGoalKeeper;
    private RecyclerView rcvListDefender;
    private RecyclerView rcvListMidFielder;
    private RecyclerView rcvListForward;
    private ListMemberClubAdapter listMemberClubAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_member_club_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        setEvent();
    }

    private void getWidget() {
        ArrayList<Player> test = new ArrayList<>();
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());
        test.add(new Player());

        rcvListGoalKeeper = (RecyclerView) view.findViewById(R.id.rcvListGoalKeeper);
        rcvListGoalKeeper.setLayoutManager(new LinearLayoutManager(getContext()));
        listMemberClubAdapter = new ListMemberClubAdapter(test, getContext(), ListMemberClubFragment.this);
        rcvListGoalKeeper.setAdapter(listMemberClubAdapter);

        rcvListDefender = (RecyclerView) view.findViewById(R.id.rcvListHauVe);
        rcvListDefender.setLayoutManager(new LinearLayoutManager(getContext()));
        listMemberClubAdapter = new ListMemberClubAdapter(test, getContext(), ListMemberClubFragment.this);
        rcvListDefender.setAdapter(listMemberClubAdapter);

        rcvListMidFielder = (RecyclerView) view.findViewById(R.id.rcvListTienVe);
        rcvListMidFielder.setLayoutManager(new LinearLayoutManager(getContext()));
        listMemberClubAdapter = new ListMemberClubAdapter(test, getContext(), ListMemberClubFragment.this);
        rcvListMidFielder.setAdapter(listMemberClubAdapter);

        rcvListForward = (RecyclerView) view.findViewById(R.id.rcvListTienDao);
        rcvListForward.setLayoutManager(new LinearLayoutManager(getContext()));
        listMemberClubAdapter = new ListMemberClubAdapter(test, getContext(), ListMemberClubFragment.this);
        rcvListForward.setAdapter(listMemberClubAdapter);
    }

    private void setEvent() {

    }

    @Override
    public void onItemClick(int i) {

    }
}
