package viewone.bean;

import exception.invalidDataException.InvalidBirthException;
import exception.invalidDataException.InvalidCredentialsException;
import exception.invalidDataException.InvalidFiscalCodeException;
import exception.invalidDataException.InvalidUserInfoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.*;

public class UserBean {


    private String username;
    private String name;
    private String surname;
    private String fiscalCode;
    private LocalDate birth;
    private String type;
    private char gender;
    private final CredentialsBean credentials;

    public UserBean(String username, String name, String surname, String fiscalCode, LocalDate birth, String type, char gender, String email, String password) {
        /*This is a constructor without syntax check and is used by controller*/
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.birth = birth;
        this.type = type;
        this.gender = gender;
        credentials = CredentialsBean.ctorWithoutSyntaxCheck(email, password);
    }

    public UserBean(String username, String name, String surname, String fiscalCode, String birth, String type, char gender, String email, String password) throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException {
        /*This is a constructor with syntax check and is used by view*/
        setUser(username, name, surname, fiscalCode, type, gender);
        setBirth(birth);
        credentials = CredentialsBean.ctorWithSyntaxCheck(email, password);
    }

    private void setUser(String username, String name, String surname, String fiscalCode, String type, char gender) throws InvalidUserInfoException, InvalidFiscalCodeException {
        setUsername(username);
        setName(name);
        setSurname(surname);
        setFc(fiscalCode);
        setType(type);
        setGender(gender);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InvalidUserInfoException {
        if(isValidLength(username, 20)){
            this.username = username;
            return;
        }
        throw new InvalidUserInfoException();
    }

    private boolean isValidLength(String str, int i) {
        return str.length() <= i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidUserInfoException {
        if(isValidLength(name, 45)){
            this.name = name;
            return;
        }
        throw new InvalidUserInfoException();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidUserInfoException {
        if(isValidLength(surname, 45)){
            this.surname = surname;
            return;
        }
        throw new InvalidUserInfoException();
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFc(String fc) throws InvalidFiscalCodeException {
        if(isValidFc(fc)) {
            this.fiscalCode = fc;
            return;
        }
        throw new InvalidFiscalCodeException();
    }

    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z])$|([0-9]{11})$",fc);
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(String birth) throws InvalidBirthException {
        if(isValidBirth(birth)) {
            this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return;
        }
        throw new InvalidBirthException();
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
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

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    public CredentialsBean getCredentials() {
        return credentials;
    }
}
