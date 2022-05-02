package model.notification;

import model.User;

import java.time.LocalDateTime;

public class DenialNotification implements Notification{
    private int id;
    private User sender;
    private User receiver;
    //private String description;
    private LocalDateTime date;

    @Override public String promptMessage() {
        return null;
    }

    @Override public User getSender() {
        return sender;
    }

    @Override public User getReceiver() {
        return receiver;
    }

    @Override public int getType() {
        return 1;
    }

    @Override public String getDescription() {
        return null;
    }

    @Override public LocalDateTime getNotificationDate() {
        return date;
    }

    @Override public int getId() {
        return id;
    }
}
