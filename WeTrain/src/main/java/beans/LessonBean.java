package beans;

import java.time.LocalTime;

public class LessonBean {
    private int id;
    private final String lessonDay;
    private final LocalTime lessonStartTime;
    private final LocalTime lessonEndTime;

    public LessonBean(int id, String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this(lessonDay, lessonStartTime, lessonEndTime);
        this.id = id;
    }

    public LessonBean(String lessonDay, LocalTime lessonStartTime, LocalTime lessonEndTime){
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
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

    public int getId() {
        return id;
    }
}
