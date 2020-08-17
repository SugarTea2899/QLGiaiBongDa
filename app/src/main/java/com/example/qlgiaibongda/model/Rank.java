package com.example.qlgiaibongda.model;

public class Rank {
    String team;
    int point;
    int goal;
    int conceded;
    int win;
    int draw;
    int loss;
    int season;

    public Rank(String team, int point, int goal, int conceded, int win, int draw, int loss, int season) {
        this.team = team;
        this.point = point;
        this.goal = goal;
        this.conceded = conceded;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
        this.season = season;
    }

    public Rank() {
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getConceded() {
        return conceded;
    }

    public void setConceded(int conceded) {
        this.conceded = conceded;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
