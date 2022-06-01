package viewone.beans;

public class EmailReceivedNotificationBean {
    private final UserBean sender;
    private final UserBean receiver;

    public EmailReceivedNotificationBean(UserBean sender, UserBean receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }
}
