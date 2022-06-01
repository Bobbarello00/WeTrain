package models.notification;

import models.User;

import java.time.LocalDateTime;

public class RejectedRequestNotification extends Notification {

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

    @Override public NotificationEnum getType() {
        return NotificationEnum.REJECTEDREQUEST;
    }

    @Override public String getDescription() {
        return "";
    }

}
