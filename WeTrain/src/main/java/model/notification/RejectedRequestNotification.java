package model.notification;

import model.User;

import java.time.LocalDateTime;

public class RejectedRequestNotification extends Notification {
    /*
    Bad news!
    Your Trainer '%s' just rejected your last workout plan request.
    Before asking a new one try getting in touch with him with an email from 'Your Personal Trainer' page!
    */

    public RejectedRequestNotification(int id, User sender, User receiver, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
    }

    public RejectedRequestNotification(User sender, User receiver) {
        super(sender, receiver);
    }

    @Override public String promptMessage() {
        return String.format("""
                Bad news!
                Your Trainer %s just rejected your last workout plan request.
                Before asking a new one try getting in touch with him with an email from 'Your Personal Trainer' page!
                """,
                sender.getName() + " " + sender.getSurname());
    }

    @Override public int getType() {
        return NotificationEnum.REJECTEDREQUEST.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }

}
