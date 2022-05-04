package model.notification;

import model.User;
import viewone.bean.UserBean;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Notification {
    int id;
    User sender;
    User receiver;
    LocalDateTime date;

    protected Notification(int id, User sender, User receiver, LocalDateTime date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public abstract String promptMessage();

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public abstract int getType();

    public abstract String getDescription();

    public LocalDateTime getNotificationDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
