package viewone.bean;

import java.time.LocalDate;

public class AthleteBean extends UserBean{
    private String cardNumber;
    private LocalDate cardExpirationDate;

    public AthleteBean() {
        super();
    }

    public AthleteBean(String username, String name, String surname, String fc) {
        super(username, name, surname, fc);
    }

    public AthleteBean(String username, String name, String surname, String fc, String cardNumber, LocalDate cardExpirationDate) {
        super(username, name, surname, fc);
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }
}
