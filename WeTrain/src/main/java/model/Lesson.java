package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private int id;
    private final String lessonDay;
    private final LocalTime lessonStartTime;
    private final LocalTime lessonEndTime;

    public Lesson(int id, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this(lessonDay, lessonStartTime, lessonEndTime);
        this.id = id;
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

    public LocalTime getLessonEndTime() {
        return lessonEndTime;
    }
}
