package model;

import java.time.LocalDate;

public class Athlete extends User {
    private String cardNumber;
    private LocalDate cardExpirationDate;

    public Athlete(String name, String surname, LocalDate dateOfBirth, String fc, String email, String password){
        super(name, surname, dateOfBirth, fc, email);
    }

    public Athlete(String name, String surname, LocalDate dateOfBirth, String fc, String email, String cardNumber, LocalDate cardExpirationDate){
        super(name, surname, dateOfBirth, fc, email);
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
