package com.example.qlgiaibongda.model;

import java.util.Date;

public class Player {
    private String name;
    private Date dob;
    private Integer type;
    private String nationality;
    private String teamId;
    private Integer totalRedCard;
    private Integer totalGoal;
    private Integer totalAssist;
    private Integer totalYellowCard;
    private Integer totalCleanSheet;
    private String avatar;
    private Integer number;

    public Player() {

    }

    public Player(String name, Date dob, Integer type, String nationality, String teamId, Integer totalRedCard, Integer totalGoal, Integer totalAssist, Integer totalYellowCard, Integer totalCleanSheet, String avatar, Integer number) {
        this.name = name;
        this.dob = dob;
        this.type = type;
        this.nationality = nationality;
        this.teamId = teamId;
        this.totalRedCard = totalRedCard;
        this.totalGoal = totalGoal;
        this.totalAssist = totalAssist;
        this.totalYellowCard = totalYellowCard;
        this.totalCleanSheet = totalCleanSheet;
        this.avatar = avatar;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getTotalRedCard() {
        return totalRedCard;
    }

    public void setTotalRedCard(Integer totalRedCard) {
        this.totalRedCard = totalRedCard;
    }

    public Integer getTotalGoal() {
        return totalGoal;
    }

    public void setTotalGoal(Integer totalGoal) {
        this.totalGoal = totalGoal;
    }

    public Integer getTotalAssist() {
        return totalAssist;
    }

    public void setTotalAssist(Integer totalAssist) {
        this.totalAssist = totalAssist;
    }

    public Integer getTotalYellowCard() {
        return totalYellowCard;
    }

    public void setTotalYellowCard(Integer totalYellowCard) {
        this.totalYellowCard = totalYellowCard;
    }

    public Integer getTotalCleanSheet() {
        return totalCleanSheet;
    }

    public void setTotalCleanSheet(Integer totalCleanSheet) {
        this.totalCleanSheet = totalCleanSheet;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
