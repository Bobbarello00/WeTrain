package com.wetrain.wetrain.Entities;

import java.time.LocalDateTime;

public class Lesson {
    private LocalDateTime lessonDate;

    public Lesson(LocalDateTime lessonDate){
        this.lessonDate = lessonDate;
    }

    public void modifyDate(LocalDateTime newDate){
        this.lessonDate = newDate;
        //TODO notifica agli iscritti la modifica
    }
}
