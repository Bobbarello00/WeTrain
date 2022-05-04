package model.notification;

public class RejectedRequestNotification extends Notification {

    /*
    Bad news!
    Your Trainer '%s' just rejected your last workout plan request.
    Before asking a new one try getting in touch with him with an email from 'Your Personal Trainer' page!
    */

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.REJECTEDREQUEST.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
    //private String description;

}
