package viewone.bean;

public class TrainerBean extends UserBean{
    private String iban;

    public TrainerBean() {
        super();
        super.type = "Trainer";
    }

    public TrainerBean(String username, String name, String surname, String fc) {
        super(username, name, surname, fc);
        super.type = "Trainer";
    }
}
