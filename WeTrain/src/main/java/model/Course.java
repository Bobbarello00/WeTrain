package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private int id;
    private final String name;
    private List<Lesson> lessonList;
    private final Trainer owner;
    private final String description;
    private final String fitnessLevel;
    private final String equipment;


    public Course(int id, String name, String description, String fitnessLevel, Trainer owner, String equipment, List<Lesson> lessonList){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
        setLessons(lessonList);
    }

    public Course(String name, String description, String fitnessLevel, Trainer owner, String equipment, List<Lesson> lessonList){
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
        setLessons(lessonList);
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

    private void setLessons(List<Lesson> list){
        lessonList = new ArrayList<>();
        for(Lesson lesson: list) {
            lessonList.add(new Lesson(
                    lesson.getId(),
                    lesson.getLessonDay(),
                    lesson.getLessonStartTime(),
                    lesson.getLessonEndTime(),
                    lesson.getStartedLessonUrl()
            ));
        }
    }
}