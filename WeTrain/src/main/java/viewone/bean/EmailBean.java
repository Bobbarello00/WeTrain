package viewone.bean;

public class EmailBean {
    private UserBean sender;
    private UserBean receiver;
    private String object;
    private String text;

    public EmailBean(UserBean sender, UserBean receiver, String object, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.object = object;
        this.text = text;
    }


    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }
}
