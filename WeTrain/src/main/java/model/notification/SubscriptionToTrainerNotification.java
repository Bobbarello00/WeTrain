package model.notification;

import model.User;

import java.time.LocalDateTime;

public class SubscriptionToTrainerNotification extends Notification {
    //private String description;

    /*
    Good news!
    The Athlete '%s' Subscribed to you.
    You are growing in popularity, now you have reached %d subscribers!
    */

    public SubscriptionToTrainerNotification(int id, User sender, User receiver, LocalDateTime date) {
        super(id, sender, receiver, date);
    }

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTIONTOTRAINER.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
