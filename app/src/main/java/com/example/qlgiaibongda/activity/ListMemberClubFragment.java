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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.adapter.ListMemberClubAdapter;
import com.example.qlgiaibongda.model.Coach;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;
import com.example.qlgiaibongda.retrofit.APIUtils;
import com.example.qlgiaibongda.retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qlgiaibongda.constant.Constant.DEFENDER;
import static com.example.qlgiaibongda.constant.Constant.FORWARD;
import static com.example.qlgiaibongda.constant.Constant.GOAL_KEEPER;
import static com.example.qlgiaibongda.constant.Constant.MIDFIELDER;

public class ListMemberClubFragment extends Fragment implements ListMemberClubAdapter.onItemClickListener {
    private RecyclerView rcvListGoalKeeper;
    private RecyclerView rcvListDefender;
    private RecyclerView rcvListMidFielder;
    private RecyclerView rcvListForward;
    private ListMemberClubAdapter listMemberClubAdapter;
    private View view;
    private TextView tvNameCoach;
    private TextView tvNationalityCoach;
    private ImageView imvCoach;
    private ImageView imvNationality;

    private Team teamInfo;
    private ArrayList<Player> listPlayer;
    private Coach coachInfo;

    public ListMemberClubFragment(Team teamInfo) {
        this.teamInfo = teamInfo;
    }

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
        rcvListGoalKeeper = (RecyclerView) view.findViewById(R.id.rcvListGoalKeeper);
        rcvListGoalKeeper.setLayoutManager(new LinearLayoutManager(getContext()));

        rcvListDefender = (RecyclerView) view.findViewById(R.id.rcvListHauVe);
        rcvListDefender.setLayoutManager(new LinearLayoutManager(getContext()));

        rcvListMidFielder = (RecyclerView) view.findViewById(R.id.rcvListTienVe);
        rcvListMidFielder.setLayoutManager(new LinearLayoutManager(getContext()));

        rcvListForward = (RecyclerView) view.findViewById(R.id.rcvListTienDao);
        rcvListForward.setLayoutManager(new LinearLayoutManager(getContext()));

        tvNameCoach = (TextView) view.findViewById(R.id.nameHlv);
        tvNationalityCoach = (TextView) view.findViewById(R.id.nationalityHlv);
        imvCoach = (ImageView) view.findViewById(R.id.imageOfHlv);
        imvNationality = (ImageView) view.findViewById(R.id.imageNationality);
    }

    private void setEvent() {
        DataClient dataClient = APIUtils.getData();

        Call<List<Player>> callBackListMember = dataClient.getListPlayerTeam(teamInfo.getId());
        callBackListMember.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    listPlayer = (ArrayList<Player>) response.body();
                    ArrayList<Player> goalKeepers = new ArrayList<>();
                    ArrayList<Player> defenders = new ArrayList<>();
                    ArrayList<Player> midfielders = new ArrayList<>();
                    ArrayList<Player> forwards = new ArrayList<>();
                    for (Player player : listPlayer) {
                        switch (player.getType()) {
                            case GOAL_KEEPER: goalKeepers.add(player); break;
                            case DEFENDER: defenders.add(player); break;
                            case MIDFIELDER: midfielders.add(player); break;
                            case FORWARD: forwards.add(player); break;
                            default:
                                break;
                        }
                    }
                    listMemberClubAdapter = new ListMemberClubAdapter(goalKeepers, getContext(), ListMemberClubFragment.this);
                    rcvListGoalKeeper.setAdapter(listMemberClubAdapter);
                    listMemberClubAdapter = new ListMemberClubAdapter(defenders, getContext(), ListMemberClubFragment.this);
                    rcvListDefender.setAdapter(listMemberClubAdapter);
                    listMemberClubAdapter = new ListMemberClubAdapter(midfielders, getContext(), ListMemberClubFragment.this);
                    rcvListMidFielder.setAdapter(listMemberClubAdapter);
                    listMemberClubAdapter = new ListMemberClubAdapter(forwards, getContext(), ListMemberClubFragment.this);
                    rcvListForward.setAdapter(listMemberClubAdapter);
                }
                else {
                    Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
            }
        });

        Call<Coach> callBackCoachInfo = dataClient.getInfoCoach(teamInfo.getCoachId());
        callBackCoachInfo.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                if (response.isSuccessful()) {
                    coachInfo = response.body();
                    tvNameCoach.setText(coachInfo.getName());
                    tvNationalityCoach.setText(coachInfo.getNationality());
                }
                else {
                    Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin đội", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int i) {

    }
}
