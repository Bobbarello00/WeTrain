package model;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private List<Lesson> lessons;
    private Trainer owner;
    private String description;
    private String fitnessLevel;
    private String equipment;

    public Course(int id, String name, String description, String fitnessLevel, Trainer owner, String equipment){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Trainer getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}