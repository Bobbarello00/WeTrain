package model.notification;

public class SubscriptionToTrainerNotification extends Notification {
    //private String description;

    /*
    Good news!
    The Athlete '%s' Subscribed to you.
    You are growing in popularity, now you have reached %d subscribers!
    */

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
