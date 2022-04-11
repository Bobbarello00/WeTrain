package viewone.bean;

import java.time.LocalTime;

public class LessonBean {
    private String lessonDay;
    private LocalTime lessonStartTime;
    private LocalTime lessonEndTime;

    public LessonBean(String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public void setLessonDay(String lessonDay) {
        this.lessonDay = lessonDay;
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
