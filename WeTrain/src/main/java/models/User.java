package models;

import models.record.Credentials;
import models.record.PersonalInfo;

import java.time.LocalDate;

public abstract class User {
    private String username;
    private Credentials credentials;
    private PersonalInfo personalInfo;

    protected User() {}

    protected User(String username, PersonalInfo personalInfo, Credentials credentials){
        this.personalInfo = personalInfo;
        this.username = username;
        this.credentials = credentials;
    }

    public String getName() {
        return personalInfo.name();
    }

    public String getSurname() {
        return personalInfo.surname();
    }

    public LocalDate getDateOfBirth() {
        return personalInfo.dateOfBirth();
    }

    public String getFiscalCode() {
        return personalInfo.fc();
    }

    public String getEmail() {
        return credentials.email();
    }

    public String getPassword() {
        return credentials.password();
    }

    public char getGender() {
        return personalInfo.gender();
    }

    public String getUsername() {
        return username;
    }
}
