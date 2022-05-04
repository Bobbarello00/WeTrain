package model.notification;

public class SubscriptionToCourseNotification extends Notification {
    //private String description;

    /*
    Good news!
    The Athlete '%s' Subscribed to your course '%s'.
    Your course is earning popularity and now its at %d subscribers!
    */

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
