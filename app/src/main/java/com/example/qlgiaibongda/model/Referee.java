package com.example.qlgiaibongda.model;

import java.util.Date;

public class Referee {
    private String name;
    private Date dob;
    private String nationality;

    public Referee(String name, Date dob, String nationality) {
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    public Referee() {}

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
