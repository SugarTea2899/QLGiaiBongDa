package com.example.qlgiaibongda.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.SelectPlayerAdapter;
import com.example.qlgiaibongda.adapter.SelectedPlayerAdapter;
import com.example.qlgiaibongda.constant.Constant;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.Player;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SelectedListFragment extends Fragment {
    private TextView tvTeam;
    private RecyclerView rcvSeletedList;
    private ImageButton btnFunction;
    private View view;
    private ArrayList<Player> listPlayer;
    private Match matchInfo;

    public SelectedListFragment(Match matchInfo) {
        this.matchInfo = matchInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selected_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        initRcv();
    }

    private void initRcv(){
        for (int i = 1; i <= 10; i++){
            Player player = new Player();
            player.setName("Ronaldo" + i);
            player.setNumber(i);
            player.setType(Constant.FORWARD);
            player.setAvatar("https://i.imgur.com/DvpvklR.png");
            listPlayer.add(player);
        }

        SelectedPlayerAdapter adapter = new SelectedPlayerAdapter(getContext(), listPlayer);
        rcvSeletedList.setAdapter(adapter);
    }
    private void getWidget(){
        listPlayer = new ArrayList<>();
        tvTeam = (TextView) view.findViewById(R.id.tvTeamName);
        btnFunction = (ImageButton) view.findViewById(R.id.btnFunction);
        rcvSeletedList = (RecyclerView) view.findViewById(R.id.rcvSelectedPlayer);
        rcvSeletedList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
