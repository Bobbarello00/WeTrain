package com.wetrain.wetrain.Entities;

import java.util.ArrayList;


public abstract class Course {
    private String name;
    private ArrayList<Lesson> lessons;
    private String owner;
    public Course(String name, ArrayList<Lesson> lessons, String owner){
        this.name = name;
        this.lessons = lessons;
        this.owner = owner;
    }
}