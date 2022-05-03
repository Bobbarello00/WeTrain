package model.notification;

import model.User;
import viewone.bean.UserBean;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Notification {
    int id = 0;
    User sender = null;
    User receiver = null;
    LocalDateTime date = null;

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
