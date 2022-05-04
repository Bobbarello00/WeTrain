package model.notification;

import model.User;

import java.time.LocalDateTime;

public class SubscriptionToTrainerNotification extends Notification {
    private final int subscribers;

    /*
    Good news!
    The Athlete '%s' Subscribed to you.
    You are growing in popularity, now you have reached %d subscribers!
    */

    public SubscriptionToTrainerNotification(int id, User sender, User receiver, LocalDateTime date, int subscribers) {
        super(id, sender, receiver, date);
        this.subscribers = subscribers;
    }

    @Override public String promptMessage() {
        return String.format("""
                Good news!
                The Athlete '%s' Subscribed to you.
                You are growing in popularity, now you have reached %d subscribers!
                """,
                sender.getName() + " " + sender.getSurname(),
                subscribers);
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTIONTOTRAINER.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
