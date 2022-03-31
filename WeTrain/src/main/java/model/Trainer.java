package model;

import java.time.LocalDate;

public class Trainer extends User {
    private String iban;

    public Trainer() {}

    public Trainer(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
    }

    public Trainer(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password, String iban){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
        this.iban = iban;
    }

    public void changeIban(String newIban){
        this.iban = newIban;
    }

    public String getIban() {
        return iban;
    }
}