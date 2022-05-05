package model.notification;

import model.User;

import java.time.LocalDateTime;

public class SubscriptionToTrainerNotification extends Notification {
    private final int subscribers;

    public SubscriptionToTrainerNotification(int id, User sender, User receiver, int subscribers, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
        this.subscribers = subscribers;
    }

    public SubscriptionToTrainerNotification(User sender, User receiver, int subscribers) {
        super(sender, receiver);
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

    @Override public NotificationEnum getType() {
        return NotificationEnum.SUBSCRIPTIONTOTRAINER;
    }

    @Override public String getDescription() {
        return null;
    }
}
