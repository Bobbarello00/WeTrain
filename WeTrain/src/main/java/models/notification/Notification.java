package models.notification;

import models.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Notification implements Serializable {
    protected int id;
    protected User sender;
    protected User receiver;
    protected LocalDateTime dateTime;

    protected Notification(int id, User sender, User receiver, LocalDateTime dateTime) {
        this(sender, receiver);
        this.id = id;
        this.dateTime = dateTime;
    }

    protected Notification(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = LocalDateTime.now();
    }

    public abstract String promptMessage();

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public abstract NotificationEnum getType();

    public abstract String getDescription();

    public LocalDateTime getNotificationDate() {
        return dateTime;
    }

    public int getId() {
        return id;
    }
}
