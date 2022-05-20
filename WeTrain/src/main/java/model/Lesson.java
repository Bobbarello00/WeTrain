package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private int id;
    private final String lessonDay;
    private final LocalTime lessonStartTime;
    private final LocalTime lessonEndTime;
    private String startedLessonUrl;

    public Lesson(int id, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime, String startedLessonUrl){
        this(lessonDay, lessonStartTime, lessonEndTime);
        this.id = id;
        this.startedLessonUrl = startedLessonUrl;
    }

    public Lesson(String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
        this.startedLessonUrl = null;
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

    public String getStartedLessonUrl() {
        return startedLessonUrl;
    }
}
