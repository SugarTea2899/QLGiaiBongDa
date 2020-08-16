package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.MatchProgressAdapter;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.MatchStatDetails;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchProgressFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private MatchProgressAdapter matchProgressAdapter;
    private Match matchInfo;
    private ArrayList<MatchStatDetails> listDetails;

    public MatchProgressFragment(Match matchInfo) {
        this.matchInfo = matchInfo;
    }

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
        recyclerView = (RecyclerView) view.findViewById(R.id.rcvProgressMatch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setEvent() {
        DataClient dataClient = APIUtils.getData();
        Call<List<MatchStatDetails>> callBackDetails = dataClient.getMatchDetails(matchInfo.getId());
        callBackDetails.enqueue(new Callback<List<MatchStatDetails>>() {
            @Override
            public void onResponse(Call<List<MatchStatDetails>> call, Response<List<MatchStatDetails>> response) {
                if (response.isSuccessful()) {
                    listDetails = (ArrayList<MatchStatDetails>) response.body();
                    for (int i = 0; i < listDetails.size(); i++) {
                        if (listDetails.get(i).getType() == 3) {
                            listDetails.remove(i);
                            i--;
                        }
                    }
                    Collections.reverse(listDetails);
                    matchProgressAdapter = new MatchProgressAdapter(getContext(), listDetails, matchInfo);
                    recyclerView.setAdapter(matchProgressAdapter);
                }
                else {
                    Toast.makeText(getContext(), "Không tìm thấy thông tin trận đấu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MatchStatDetails>> call, Throwable t) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin trận đấu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
