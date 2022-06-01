package beans;

import java.time.LocalDateTime;

public class NotificationBean {
    private int id;
    private UserBean sender;
    private UserBean receiver;
    private LocalDateTime dateTime;
    private String type;
    private String text;


    public NotificationBean(int id, UserBean sender, UserBean receiver, LocalDateTime dateTime, String type, String text) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.type = type;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}
