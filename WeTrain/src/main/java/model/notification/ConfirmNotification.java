package model.notification;

public class ConfirmNotification extends Notification{


    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.CONFIRM.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
