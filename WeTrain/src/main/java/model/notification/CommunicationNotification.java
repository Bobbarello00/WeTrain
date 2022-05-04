package model.notification;

import model.User;

import java.time.LocalDateTime;

public class CommunicationNotification extends Notification{
    private final String text;
    /*
     COURSE COMMUNICATION!
     *text*
     */
    protected CommunicationNotification(int id, User sender, User receiver, LocalDateTime date, String text) {
        super(id, sender, receiver, date);
        this.text = text;
    }

    @Override public String promptMessage() {
        return String.format("""
                COURSE COMMUNICATION!
                %s
                """,
                text);
    }

    @Override public int getType() {
        return NotificationEnum.COMMUNICATION.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
