package com.example.qlgiaibongda.model;

import java.util.Date;

public class Coach {
    private String name;
    private Date dob;
    private String nationality;

    public Coach(String name, Date dob, String nationality) {
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    public Coach() {

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
}
