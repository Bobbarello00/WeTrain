package model;

import java.util.List;

public class Course {
    private String name;
    private List<Lesson> lessons;
    private String owner;
    private String fitnessLevel;
    private String equipment;

    public Course(String name, List<Lesson> lessons, String fitnessLevel, String owner, String equipment){
        this.name = name;
        this.lessons = lessons;
        this.owner = owner;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

}