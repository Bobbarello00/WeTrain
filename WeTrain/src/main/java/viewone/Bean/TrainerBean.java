package viewone.Bean;

public class TrainerBean extends UserBean{
    private String iban;

    public TrainerBean() {
        super();
    }

    public TrainerBean(String username, String name, String surname, String fc) {
        super(username, name, surname, fc);
    }

    public TrainerBean(String username, String name, String surname, String fc, String iban) {
        super(username, name, surname, fc);
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
