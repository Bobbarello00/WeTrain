package com.wetrain.wetrain.Entities;

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
        //TODO notifica agli iscritti la modifica
    }
}
