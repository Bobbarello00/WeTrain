package model.notification;

import model.User;

import java.time.LocalDateTime;

public class SubscriptionToCourseNotification extends Notification {
    //private String description;

    /*
    Good news!
    The Athlete '%s' Subscribed to your course '%s'.
    Your course is earning popularity and now its at %d subscribers!
    */

    public SubscriptionToCourseNotification(int id, User sender, User receiver, LocalDateTime date) {
        super(id, sender, receiver, date);
    }

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTIONTOCOURSE.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
