package model.notification;

import model.User;

import java.time.LocalDateTime;

public class SubscriptionNotification extends Notification {
    private int id;
    private User sender;
    private User receiver;
    private LocalDateTime date;
    //private String description;

    @Override public String promptMessage() {
        return null;
    }

    @Override public User getSender() {
        return null;
    }

    @Override public User getReceiver() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTION.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }

    @Override public LocalDateTime getNotificationDate() {
        return null;
    }

    @Override public int getId() {
        return 0;
    }
}
