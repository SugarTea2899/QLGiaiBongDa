package com.example.qlgiaibongda.adapter;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemMatchRound {
    String textHomeTeamName;
    String textAwayTeamName;
    String textMatchTimeInfo;
    int imgHomeTeam;
    int imgAwayTeam;
    int imgMatchMoreInfo;

    public ItemMatchRound(String textHomeTeamName, String textAwayTeamName, String textMatchTimeInfo, int imgHomeTeam, int imgAwayTeam, int imgMatchMoreInfo) {
        this.textHomeTeamName = textHomeTeamName;
        this.textAwayTeamName = textAwayTeamName;
        this.textMatchTimeInfo = textMatchTimeInfo;
        this.imgHomeTeam = imgHomeTeam;
        this.imgAwayTeam = imgAwayTeam;
        this.imgMatchMoreInfo = imgMatchMoreInfo;
    }

    public ItemMatchRound() {
    }
}
