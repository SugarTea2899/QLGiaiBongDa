package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Match;

public class StatisticMatchFragment extends Fragment {
    private View view;
    private Match matchInfo;
    private TextView tvHomeTeam;
    private TextView tvHomeGoal;
    private TextView tvHomeLose;
    private TextView tvHomeYellowCard;
    private TextView tvHomeRedCard;
    private TextView tvGuestTeam;
    private TextView tvGuestGoal;
    private TextView tvGuestLose;
    private TextView tvGuestYellowCard;
    private TextView tvGuestRedCard;

    public StatisticMatchFragment(Match matchInfo) {
        this.matchInfo = matchInfo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statistic_match, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
    }

    private void getWidget(){
        tvHomeTeam = (TextView) view.findViewById(R.id.tvHomeTeam);
        tvHomeGoal = (TextView) view.findViewById(R.id.tvHomeGoal);
        tvHomeLose = (TextView) view.findViewById(R.id.tvHomeLose);
        tvHomeYellowCard = (TextView) view.findViewById(R.id.tvHomeYellowCard);
        tvHomeRedCard = (TextView) view.findViewById(R.id.tvHomeRedCard);
        tvGuestTeam = (TextView) view.findViewById(R.id.tvGuestTeam);
        tvGuestGoal = (TextView) view.findViewById(R.id.tvGuestGoal);
        tvGuestLose = (TextView) view.findViewById(R.id.tvGuestLose);
        tvGuestYellowCard = (TextView) view.findViewById(R.id.tvGuestYellowCard);
        tvGuestRedCard = (TextView) view.findViewById(R.id.tvGuestRedCard);

        tvHomeTeam.setText(matchInfo.getHomeTeam());
        tvHomeGoal.setText(matchInfo.getHomeGoal().toString());
        tvHomeLose.setText(matchInfo.getGuestGoal().toString());
        tvHomeYellowCard.setText(matchInfo.getHomeYellowCard().toString());
        tvHomeRedCard.setText(matchInfo.getHomeRedCard().toString());
        tvGuestTeam.setText(matchInfo.getGuestTeam());
        tvGuestGoal.setText(matchInfo.getGuestGoal().toString());
        tvGuestLose.setText(matchInfo.getHomeGoal().toString());
        tvGuestYellowCard.setText(matchInfo.getGuestYellowCard().toString());
        tvGuestRedCard.setText(matchInfo.getGuestRedCard().toString());
    }
}
