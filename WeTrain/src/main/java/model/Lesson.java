package model;

import java.time.LocalDateTime;

public class Lesson {
    private LocalDateTime lessonDate;
    private Course course;

    public Lesson(Course course, LocalDateTime lessonDate){
        this.course = course;
        this.lessonDate = lessonDate;
    }

    public void modifyDate(LocalDateTime newDate){
        this.lessonDate = newDate;
        //Notifica agli iscritti la modifica
    }
}
