package model;

import java.time.LocalDate;

public class Athlete extends User {
    private String cardNumber;
    private LocalDate cardExpirationDate;
    public Athlete(String name, LocalDate dateOfBirth, String fc, String email, String cardNumber, LocalDate cardExpirationDate){
        super(name, dateOfBirth, fc, email);
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
    }
    public void changeCardInfo(String newCardNumber, LocalDate newCardExpirationDate){
        this.cardNumber = newCardNumber;
        this.cardExpirationDate = newCardExpirationDate;
    }
}
