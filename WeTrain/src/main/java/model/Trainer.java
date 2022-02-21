package model;

import java.time.LocalDate;

public class Trainer extends User {
    private String iban;
    public Trainer(String name, LocalDate dateOfBirth, String fc, String email, String iban){
        super(name, dateOfBirth, fc, email);
        this.iban = iban;
    }
    public void changeIban(String newIban){
        this.iban = newIban;
    }
}