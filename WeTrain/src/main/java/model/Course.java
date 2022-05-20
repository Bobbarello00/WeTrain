package model;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private int id;
    private final String name;
    private List<Lesson> lessonList;
    private final Trainer owner;
    private final String description;
    private final String fitnessLevel;
    private final String equipment;
    private String startedLessonUrl;

    public Course(int id, String name, String description, String fitnessLevel, Trainer owner, String equipment, String startedLessonUrl){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
        this.startedLessonUrl = startedLessonUrl;
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

    public void setLessons(List<Lesson> list){
        lessonList = list;
    }

    public String getStartedLessonUrl() {
        return startedLessonUrl;
    }
}