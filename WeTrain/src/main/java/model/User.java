package model;

import java.time.LocalDate;

public abstract class User {
    private String name;
    private String surname;
    private String username;
    private LocalDate dateOfBirth;
    private String fiscalCode;
    private String email;
    private char gender;
    private String password;

    protected User() {}

    protected User(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.fiscalCode = fc;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
