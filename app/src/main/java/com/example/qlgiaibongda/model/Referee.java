package com.example.qlgiaibongda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Referee {
    @SerializedName("_id")
    @Expose
    private String id;

    private String name;
    private Date dob;
    private String nationality;
    private String avatar;

    public Referee(String name, Date dob, String nationality, String avatar) {
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
        this.avatar = avatar;
    }

    public Referee() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
