package model.notification;

public class SubscriptionNotification extends Notification {
    //private String description;

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTION.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
