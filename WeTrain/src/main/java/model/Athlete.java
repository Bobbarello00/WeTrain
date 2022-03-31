package model;

import java.time.LocalDate;

public class Athlete extends User {
    private String cardNumber;
    private LocalDate cardExpirationDate;

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
    }

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password, String cardNumber, LocalDate cardExpirationDate){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
    }
    public void changeCardInfo(String newCardNumber, LocalDate newCardExpirationDate){
        this.cardNumber = newCardNumber;
        this.cardExpirationDate = newCardExpirationDate;
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
