package com.example.qlgiaibongda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlgiaibongda.R;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.MatchStatDetails;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MatchStatDetails> list;
    private Match matchInfo;

    private static final int GOAL = 0;
    private static final int PENALTY = 1;
    private static final int OG = 2;
    private static final int YELLOW = 4;
    private static final int RED = 5;
    private static final int SUBSTITUTE = 6;

    public MatchProgressAdapter(Context context, List<MatchStatDetails> list, Match matchInfo) {
        this.context = context;
        this.list = list;
        this.matchInfo = matchInfo;
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()) {
            case 0: return GOAL;
            case 1: return PENALTY;
            case 2: return OG;
            case 3: return -1;
            case 4: return YELLOW;
            case 5: return RED;
            case 6: return SUBSTITUTE;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView;
        switch (viewType) {
            case GOAL: case PENALTY:
                itemView = layoutInflater.inflate(R.layout.goal_item_rcv, parent, false);
                return new GoalItem(itemView);
            case OG:
                itemView = layoutInflater.inflate(R.layout.owngoal_item_rcv, parent, false);
                return new OwnGoal(itemView);
            case YELLOW:
                itemView = layoutInflater.inflate(R.layout.yellowcard_item_rcv, parent, false);
                return new YellowItem(itemView);
            case RED:
                itemView = layoutInflater.inflate(R.layout.redcard_item_rcv, parent, false);
                return new RedItem(itemView);
            case SUBSTITUTE:
                itemView = layoutInflater.inflate(R.layout.substitution_item_rcv, parent, false);
                return new SubstituteItem(itemView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MatchStatDetails matchStatDetails = list.get(position);
        switch (getItemViewType(position)) {
            case GOAL:
                GoalItem goalViewHolder = (GoalItem) holder;
                goalViewHolder.minute.setText(matchStatDetails.getMinute().toString() + "'");
                goalViewHolder.namePlayer.setText(matchStatDetails.getPlayerIn().getName());
                goalViewHolder.type.setVisibility(View.INVISIBLE);
                goalViewHolder.nameClub.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            case PENALTY:
                GoalItem penaltyViewHolder = (GoalItem) holder;
                penaltyViewHolder.minute.setText(matchStatDetails.getMinute().toString() + "'");
                penaltyViewHolder.namePlayer.setText(matchStatDetails.getPlayerIn().getName());
                penaltyViewHolder.nameClub.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            case OG:
                OwnGoal ogViewHolder = (OwnGoal) holder;
                ogViewHolder.minute.setText(matchStatDetails.getMinute().toString() + "'");
                ogViewHolder.namePlayer.setText(matchStatDetails.getPlayerIn().getName());
                ogViewHolder.nameClub.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            case YELLOW:
                YellowItem yellowViewHoler = (YellowItem) holder;
                yellowViewHoler.minute.setText(matchStatDetails.getMinute().toString() + "'");
                yellowViewHoler.namePlayer.setText(matchStatDetails.getPlayerIn().getName());
                yellowViewHoler.nameClub.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            case RED:
                RedItem redViewHolder = (RedItem) holder;
                redViewHolder.minute.setText(matchStatDetails.getMinute().toString() + "'");
                redViewHolder.namePlayer.setText(matchStatDetails.getPlayerIn().getName());
                redViewHolder.nameClub.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            case SUBSTITUTE:
                SubstituteItem substituteViewHolder = (SubstituteItem) holder;
                substituteViewHolder.minute.setText(matchStatDetails.getMinute().toString() + "'");
                substituteViewHolder.namePlayerIn.setText(matchStatDetails.getPlayerIn().getName());
                substituteViewHolder.nameClubIn.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                substituteViewHolder.namePlayerOut.setText(matchStatDetails.getPlayerOut().getName());
                substituteViewHolder.nameClubOut.setText(matchStatDetails.getHomeTeam() ? matchInfo.getHomeTeam() : matchInfo.getGuestTeam());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GoalItem extends RecyclerView.ViewHolder {
        private TextView minute;
        private TextView namePlayer;
        private TextView nameClub;
        private CircleImageView imagePlayer;
        private TextView type;

        public GoalItem(@NonNull View itemView) {
            super(itemView);
            minute = (TextView) itemView.findViewById(R.id.minuteOfMatch);
            namePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            nameClub = (TextView) itemView.findViewById(R.id.nameClub);
            imagePlayer = (CircleImageView) itemView.findViewById(R.id.imageOfPlayer);
            type = (TextView) itemView.findViewById(R.id.typeGoal);
        }
    }

    public class OwnGoal extends RecyclerView.ViewHolder {
        private TextView minute;
        private TextView namePlayer;
        private TextView nameClub;
        private CircleImageView imagePlayer;
        public OwnGoal(@NonNull View itemView) {
            super(itemView);
            minute = (TextView) itemView.findViewById(R.id.minuteOfMatch);
            namePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            nameClub = (TextView) itemView.findViewById(R.id.nameClub);
            imagePlayer = (CircleImageView) itemView.findViewById(R.id.imageOfPlayer);
        }
    }

    public class YellowItem extends RecyclerView.ViewHolder {
        private TextView minute;
        private TextView namePlayer;
        private TextView nameClub;
        private CircleImageView imagePlayer;

        public YellowItem(@NonNull View itemView) {
            super(itemView);
            minute = (TextView) itemView.findViewById(R.id.minuteOfMatch);
            namePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            nameClub = (TextView) itemView.findViewById(R.id.nameClub);
            imagePlayer = (CircleImageView) itemView.findViewById(R.id.imageOfPlayer);
        }
    }

    public class RedItem extends RecyclerView.ViewHolder {
        private TextView minute;
        private TextView namePlayer;
        private TextView nameClub;
        private CircleImageView imagePlayer;

        public RedItem(@NonNull View itemView) {
            super(itemView);
            minute = (TextView) itemView.findViewById(R.id.minuteOfMatch);
            namePlayer = (TextView) itemView.findViewById(R.id.namePlayer);
            nameClub = (TextView) itemView.findViewById(R.id.nameClub);
            imagePlayer = (CircleImageView) itemView.findViewById(R.id.imageOfPlayer);
        }
    }

    public class SubstituteItem extends RecyclerView.ViewHolder {
        private TextView minute;
        private TextView namePlayerIn;
        private TextView nameClubIn;
        private CircleImageView imagePlayerIn;
        private TextView namePlayerOut;
        private TextView nameClubOut;
        private CircleImageView imagePlayerOut;

        public SubstituteItem(@NonNull View itemView) {
            super(itemView);
            minute = (TextView) itemView.findViewById(R.id.minuteOfMatch);
            namePlayerIn = (TextView) itemView.findViewById(R.id.namePlayerIn);
            nameClubIn = (TextView) itemView.findViewById(R.id.nameClubIn);
            imagePlayerIn = (CircleImageView) itemView.findViewById(R.id.imageOfPlayerIn);
            namePlayerOut = (TextView) itemView.findViewById(R.id.namePlayerOut);
            nameClubOut = (TextView) itemView.findViewById(R.id.nameClubOut);
            imagePlayerOut = (CircleImageView) itemView.findViewById(R.id.imageOfPlayerOut);
        }
    }
}
