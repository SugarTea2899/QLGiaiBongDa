package com.example.qlgiaibongda.model;

public class Team {
    private String name;
    private String shortName;
    private String stadium;
    private String sponsor;
    private String captainId;
    private String coachId;
    private Integer currentRanking;
    private String logo;

    public Team() {

    }

    public Team(String name, String shortName, String stadium, String sponsor, String captainId, String coachId, Integer currentRanking, String logo) {
        this.name = name;
        this.shortName = shortName;
        this.stadium = stadium;
        this.sponsor = sponsor;
        this.captainId = captainId;
        this.coachId = coachId;
        this.currentRanking = currentRanking;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getCaptainId() {
        return captainId;
    }

    public void setCaptainId(String captainId) {
        this.captainId = captainId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public Integer getCurrentRanking() {
        return currentRanking;
    }

    public void setCurrentRanking(Integer currentRanking) {
        this.currentRanking = currentRanking;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
