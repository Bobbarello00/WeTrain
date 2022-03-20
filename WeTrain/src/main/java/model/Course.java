package model;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private List<Lesson> lessons;
    private String owner;
    private String description;
    private String fitnessLevel;
    private String equipment;

    public Course(int id, String name, List<Lesson> lessons, String description, String fitnessLevel, String owner, String equipment){
        this.id = id;
        this.name = name;
        this.lessons = lessons;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

}