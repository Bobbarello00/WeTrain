package viewone.bean;

public class EmailBean {
    private UserBean sender;
    private UserBean receiver;

    public EmailBean(UserBean sender, UserBean receiver) {
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
