package model.notification;

import model.User;

import java.time.LocalDateTime;

public class EmailReceivedNotification extends Notification{

    public EmailReceivedNotification(int id, User sender, User receiver, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
    }

    public EmailReceivedNotification(User sender, User receiver) {
        super(sender, receiver);
    }

    @Override
    public String promptMessage() {
        return String.format("""
                Check your inbox!
                %s sent you an email.
                Check your inbox, if it's not there be sure to have inserted the right email on your profile!
                """,
                sender.getName() + " " + sender.getSurname());
    }

    @Override
    public NotificationEnum getType() {
        return NotificationEnum.EMAILRECEIVED;
    }

    @Override
    public String getDescription() {
        return "";
    }
}
