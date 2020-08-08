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

import org.w3c.dom.Text;

public class ClubStatisticalFragment extends Fragment {
    private View view;
    private TextView tvGoals;
    private TextView tvLoses;
    private TextView tvHelps;
    private TextView tvSachLuoi;
    private TextView tvYellowCard;
    private TextView tvRedCard;

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
    }

    private void getWidget() {
        tvGoals = (TextView) view.findViewById(R.id.goalsCount);
        tvLoses = (TextView) view.findViewById(R.id.losesCount);
        tvHelps = (TextView) view.findViewById(R.id.helpsCount);
        tvSachLuoi = (TextView) view.findViewById(R.id.sachLuoiCount);
        tvYellowCard = (TextView) view.findViewById(R.id.yellowCount);
        tvRedCard = (TextView) view.findViewById(R.id.redCount);
    }
}
