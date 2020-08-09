package com.example.qlgiaibongda.adapter;

public class ItemRankTeam {
    String textRank;
    int imgStatus;
    int imgTeamLogo;
    String textTeamName;
    String textRound;
    String textGoalDiff;
    String textPoint;

    public ItemRankTeam(String textRank, int imgStatus, int imgTeamLogo, String textTeamName, String textRound, String textGoalDiff, String textPoint) {
        this.textRank = textRank;
        this.imgStatus = imgStatus;
        this.imgTeamLogo = imgTeamLogo;
        this.textTeamName = textTeamName;
        this.textRound = textRound;
        this.textGoalDiff = textGoalDiff;
        this.textPoint = textPoint;
    }

    public ItemRankTeam() {}
}
