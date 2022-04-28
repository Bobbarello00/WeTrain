package model;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private int id;
    private String name;
    private List<Lesson> lessonList;
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

    public Course(String name, String description, String fitnessLevel, Trainer owner, String equipment){
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
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

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public void addAllLessons(List<Lesson> list){
        lessonList = list;
    }
}