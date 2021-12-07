package com.wetrain.wetrain.Entities;

import java.util.ArrayList;

public class Course {
    private String name;
    private ArrayList<Lesson> lessons;
    private String owner;
    private String equipment;

    public Course(String name, ArrayList<Lesson> lessons, String owner, String equipment){
        this.name = name;
        this.lessons = lessons;
        this.owner = owner;
        this.equipment = equipment;
    }

}