package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private int id;
    private String lessonDay;
    private LocalTime lessonStartTime;
    private LocalTime lessonEndTime;

    public Lesson(int id, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.id = id;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public Lesson(String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public int getId() {
        return id;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public LocalTime getLessonStartTime() {
        return lessonStartTime;
    }

    public void setLessonStartTime(LocalTime lessonStartTime) {
        this.lessonStartTime = lessonStartTime;
    }

    public LocalTime getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(LocalTime lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }
}
