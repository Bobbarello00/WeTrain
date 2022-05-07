package model.notification;

import model.User;

public class EmailReceivedNotification extends Notification{

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
