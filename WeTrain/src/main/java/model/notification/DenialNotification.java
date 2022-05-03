package model.notification;

public class DenialNotification extends Notification {

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.DENIAL.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
    //private String description;

}
