package viewone.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.*;

public class UserBean {
    private String username;
    private String name;
    private String surname;
    private String fc;
    private LocalDate birth;
    protected String type;
    private char gender;

    public UserBean() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        if(isValidLength(username, 20)){
            this.username = username;
            return true;
        }
        return false;
    }

    private boolean isValidLength(String str, int i) {
        return str.length() <= i;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(isValidLength(name, 45)){
            this.name = name;
            return true;
        }
        return false;
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        if(isValidLength(surname, 45)){
            this.surname = surname;
            return true;
        }
        return false;
    }

    public String getFc() {
        return fc;
    }

    public boolean setFc(String fc) {
        if(isValidFc(fc)) {
            this.fc = fc;
            return true;
        }
        return false;
    }

    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z])$|([0-9]{11})$",fc);
    }

    public LocalDate getBirth() {
        return birth;
    }

    public boolean setBirth(String birth) {
        if(isValidBirth(birth)) {
            this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        }
        return false;
    }

    private static boolean isValidBirth(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(value, formatter);
            String result = ld.format(formatter);
            return result.equals(value);
        } catch (DateTimeParseException exp) {
            //exp.printStackTrace();
        }
        return false;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
