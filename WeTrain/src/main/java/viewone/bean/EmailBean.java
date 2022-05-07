package viewone.bean;

public class EmailBean {
    private UserBean sender;
    private UserBean receiver;
    private String text;

    public EmailBean(UserBean sender, UserBean receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }


    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }
}
