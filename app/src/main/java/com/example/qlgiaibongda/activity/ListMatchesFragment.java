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
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMatchAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMatchesFragment extends Fragment implements ListMatchAdapter.onItemClickListener {
    private View view;
    private RecyclerView rcvListMatch;
    private ListMatchAdapter listMatchAdapter;
    private Team teamInfo;
    private ArrayList<Match> listMatch;

    public ListMatchesFragment(Team teamInfo) {
        this.teamInfo = teamInfo;
    }

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
        rcvListMatch = (RecyclerView) view.findViewById(R.id.rcvListMatchesFragment);
        rcvListMatch.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setEvent() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Match>> callBackListMatch = dataClient.getListMatchOfTeam(teamInfo.getName(), 2);
        callBackListMatch.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    listMatch = (ArrayList<Match>) response.body();
                    Collections.sort(listMatch);
                    listMatchAdapter = new ListMatchAdapter(listMatch, getContext(), ListMatchesFragment.this);
                    rcvListMatch.setAdapter(listMatchAdapter);
                }
                else {
                    Toast.makeText(getContext(), "Không thể tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Toast.makeText(getContext(), "Không thể tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int i) {

    }
}
