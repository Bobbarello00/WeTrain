package model.notification;

import model.User;
import viewone.bean.UserBean;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Notification {
    String promptMessage();

    User getSender();

    User getReceiver();

    int getType();

    String getDescription();

    LocalDateTime getNotificationDate();

    int getId();
}
