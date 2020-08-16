package com.example.qlgiaibongda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchStatDetails {
    @SerializedName("_id")
    @Expose
    private String id;

    String matchId;
    Integer type;
    Integer minute;
    Boolean isHomeTeam;
    String playerId;
    String outId;
    String inId;
    Player playerIn;
    Player playerOut;

    public void setPlayerOut(Player playerOut) {
        this.playerOut = playerOut;
    }

    public MatchStatDetails(Integer type) {
        this.type = type;
    }

    public MatchStatDetails(String matchId, Integer type, Integer minute, Boolean isHomeTeam, String playerId, String outId, String inId, Player playerIn, Player playerOut) {
        this.matchId = matchId;
        this.type = type;
        this.minute = minute;
        this.isHomeTeam = isHomeTeam;
        this.playerId = playerId;
        this.outId = outId;
        this.inId = inId;
        this.playerIn = playerIn;
        this.playerOut = playerOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Boolean getHomeTeam() {
        return isHomeTeam;
    }

    public void setHomeTeam(Boolean homeTeam) {
        isHomeTeam = homeTeam;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getInId() {
        return inId;
    }

    public void setInId(String inId) {
        this.inId = inId;
    }

    public Player getPlayerIn() {
        return playerIn;
    }

    public void setPlayerIn(Player playerIn) {
        this.playerIn = playerIn;
    }

    public Player getPlayerOut() {
        return playerOut;
    }

    public void setPlayOut(Player playerOut) {
        this.playerOut = playerOut;
    }
}
