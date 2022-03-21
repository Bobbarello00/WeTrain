package model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private int type;
    private String description;
    private LocalDateTime notificationDate;
    private User user;

    public Notification(int id, int type, String description, LocalDateTime notificationDate, User user){
        this.id = id;
        this.type = type;
        this.description = description;
        this.notificationDate = notificationDate;
        this.user = user;
    }

}
