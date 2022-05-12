package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;

public class Trainer extends User implements Serializable {
    private String iban;

    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials){
        super(username, personalInfo, credentials);
    }

    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials, String iban){
        super(username, personalInfo, credentials);
        this.iban = iban;
    }

    public void setIban(String newIban){
        this.iban = newIban;
    }

    public String getIban() {
        return iban;
    }
}