package viewone.bean;

import java.sql.Time;

public class LessonBean {
    private String lessonDay;
    private Time lessonStartTime;
    private Time lessonEndTime;

    public LessonBean(String lessonDay, Time lessonStartTime, Time lessonEndTime){
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

    public Time getLessonStartTime() {
        return lessonStartTime;
    }

    public void setLessonStartTime(Time lessonStartTime) {
        this.lessonStartTime = lessonStartTime;
    }

    public Time getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(Time lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }
}
