package model;

import java.time.LocalDateTime;

public class Lesson {
    private int id;
    private LocalDateTime lessonDate;
    private Course course;

    public Lesson(int id, Course course, LocalDateTime lessonDate){
        this.id = id;
        this.course = course;
        this.lessonDate = lessonDate;
    }

    public void modifyDate(LocalDateTime newDate){
        this.lessonDate = newDate;
        //Notifica agli iscritti la modifica
    }
}
