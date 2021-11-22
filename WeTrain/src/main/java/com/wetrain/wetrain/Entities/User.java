package com.wetrain.wetrain.Entities;

public abstract class User {
    private String name;
    private int day;
    private int month;
    private int year;
    private String fiscalCode;
    private String email;

    public User(String name, int day, int month, int year, String fc, String email){
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.fiscalCode = fc;
        this.email = email;
    }
}
