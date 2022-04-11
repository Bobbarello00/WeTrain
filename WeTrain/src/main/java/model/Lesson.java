package model;

import java.time.LocalTime;

public class Lesson {
    private int id;
    private String lessonDay;
    private LocalTime lessonStartTime;
    private LocalTime lessonEndTime;
    private Course course;

    public Lesson(int id, Course course, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.id = id;
        this.course = course;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public Lesson(Course course, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
