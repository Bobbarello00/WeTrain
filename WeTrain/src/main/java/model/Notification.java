package model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private int type;
    private String description;
    private LocalDateTime notificationDate;
    private Athlete athlete;
    private Trainer trainer;
    public Notification(int id, int type, String description, LocalDateTime notificationDate, Athlete athlete, Trainer trainer){
        this.id = id;
        this.type = type;
        this.description = description;
        this.notificationDate = notificationDate;
        this.athlete = athlete;
        this.trainer = trainer;
    }

}
