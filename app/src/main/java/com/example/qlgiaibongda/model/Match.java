package com.example.qlgiaibongda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Match implements Comparable<Match>{
    @SerializedName("_id")
    @Expose
    private String id;

    private String homeTeam;
    private String guestTeam;
    private Date dateStart;
    private String stadium;
    private String refereeId;
    private Integer round;
    private Integer homeGoal;
    private Integer guestGoal;
    private Integer homeYellowCard;
    private Integer guestYellowCard;
    private Integer homeRedCard;
    private Integer guestRedCard;
    private Integer stateMatch;

    public Match() {

    }

    public Match(String homeTeam, String guestTeam, Date dateStart, String stadium, String refereeId, Integer round, Integer homeGoal, Integer guestGoal, Integer homeYellowCard, Integer guestYellowCard, Integer homeRedCard, Integer guestRedCard, Integer stateMatch) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.dateStart = dateStart;
        this.stadium = stadium;
        this.refereeId = refereeId;
        this.round = round;
        this.homeGoal = homeGoal;
        this.guestGoal = guestGoal;
        this.homeYellowCard = homeYellowCard;
        this.guestYellowCard = guestYellowCard;
        this.homeRedCard = homeRedCard;
        this.guestRedCard = guestRedCard;
        this.stateMatch = stateMatch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getHomeGoal() {
        return homeGoal;
    }

    public void setHomeGoal(Integer homeGoal) {
        this.homeGoal = homeGoal;
    }

    public Integer getGuestGoal() {
        return guestGoal;
    }

    public void setGuestGoal(Integer guestGoal) {
        this.guestGoal = guestGoal;
    }

    public Integer getHomeYellowCard() {
        return homeYellowCard;
    }

    public void setHomeYellowCard(Integer homeYellowCard) {
        this.homeYellowCard = homeYellowCard;
    }

    public Integer getGuestYellowCard() {
        return guestYellowCard;
    }

    public void setGuestYellowCard(Integer guestYellowCard) {
        this.guestYellowCard = guestYellowCard;
    }

    public Integer getHomeRedCard() {
        return homeRedCard;
    }

    public void setHomeRedCard(Integer homeRedCard) {
        this.homeRedCard = homeRedCard;
    }

    public Integer getGuestRedCard() {
        return guestRedCard;
    }

    public void setGuestRedCard(Integer guestRedCard) {
        this.guestRedCard = guestRedCard;
    }

    public Integer getStateMatch() {
        return stateMatch;
    }

    public void setStateMatch(Integer stateMatch) {
        this.stateMatch = stateMatch;
    }

    @Override
    public int compareTo(Match o) {
        if (this.getDateStart().getTime() < o.getDateStart().getTime())
            return 1;
        else if (this.getDateStart().getTime() < o.getDateStart().getTime())
            return -1;
        return 0;
    }
}
