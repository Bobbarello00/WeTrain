package viewone.bean;

public class EmailBean {
    private UserBean sender;
    private UserBean receiver;
    private String object;
    private String body;

    public EmailBean(UserBean sender, UserBean receiver, String object, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.object = object;
        this.body = text;
    }


    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }

    public String getObject() {
        return object;
    }

    public String getBody() {
        return body;
    }
}
