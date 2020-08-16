package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubStatisticalFragment extends Fragment {
    private View view;
    private TextView tvGoals;
    private TextView tvLoses;
    private TextView tvSachLuoi;
    private TextView tvYellowCard;
    private TextView tvRedCard;

    private Team teamInfo;
    private ArrayList<Match> listMatch;
    private Integer countGoals = 0, countLoses = 0, countSachLuoi = 0, countYellow = 0, countRed = 0;

    public ClubStatisticalFragment(Team teamInfo) {
        this.teamInfo = teamInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statistical, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        setEvent();
    }

    private void getWidget() {
        tvGoals = (TextView) view.findViewById(R.id.goalsCount);
        tvLoses = (TextView) view.findViewById(R.id.losesCount);
        tvSachLuoi = (TextView) view.findViewById(R.id.sachLuoiCount);
        tvYellowCard = (TextView) view.findViewById(R.id.yellowCount);
        tvRedCard = (TextView) view.findViewById(R.id.redCount);
    }

    private void setEvent() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Match>> getListMatch = dataClient.getListMatchOfTeam(teamInfo.getName(), 2);
        getListMatch.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    listMatch = (ArrayList<Match>) response.body();
                    for (Match match : listMatch) {
                        countGoals += match.getHomeGoal();
                        countLoses += match.getGuestGoal();
                        countSachLuoi += match.getGuestGoal() == 0 ? 1 : 0;
                        countYellow += match.getHomeYellowCard();
                        countRed += match.getHomeRedCard();
                    }
                    tvGoals.setText(countGoals.toString());
                    tvLoses.setText(countLoses.toString());
                    tvSachLuoi.setText(countSachLuoi.toString());
                    tvYellowCard.setText(countYellow.toString());
                    tvRedCard.setText(countRed.toString());
                }
                else {
                    Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
