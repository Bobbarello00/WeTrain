package model.notification;

import model.User;

import java.time.LocalDateTime;

public class CommunicationNotification extends Notification{

    protected CommunicationNotification(int id, User sender, User receiver, LocalDateTime date) {
        super(id, sender, receiver, date);
    }

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.COMUNICATION.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
