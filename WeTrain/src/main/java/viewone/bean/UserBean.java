package viewone.bean;

import exception.invalid_data_exception.*;
import model.record.PersonalInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.*;

public class UserBean {

    private String username;
    private PersonalInfo personalInfo;
    private String type;
    private final CredentialsBean credentials;

    public UserBean(String username, String type, PersonalInfo personalInfo, CredentialsBean credentials) {
        /*This is a constructor without syntax check and is used by controller*/
        this.username = username;
        this.personalInfo = personalInfo;
        this.type = type;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public UserBean(String username, String name, String surname, String fiscalCode, String birth, String type, char gender, String email, String password) throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException {
        /*This is a constructor with syntax check and is used by view*/
        setUser(username, name, surname, fiscalCode, type, gender);
        birth = checkBirth(birth);
        credentials = CredentialsBean.ctorWithSyntaxCheck(email, password);
    }

    private void setUser(String username, String name, String surname, String fiscalCode, String type, char gender) throws InvalidUserInfoException, InvalidFiscalCodeException, EmptyFieldsException {
        setUsername(username);
        setType(type);
        checkName(name);
        checkSurname(surname);
        checkFc(fiscalCode);
        checkGender(gender);
        this.personalInfo = personalInfo;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) throws InvalidUserInfoException, EmptyFieldsException {
        if(username.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidLength(username, 20)){
            this.username = username;
        }else {
            throw new InvalidUserInfoException();
        }
    }

    private boolean isValidLength(String str, int i) {
        return str.length() <= i;
    }

    public String getName() {
        return personalInfo.name();
    }

    private void checkName(String name) throws InvalidUserInfoException, EmptyFieldsException {
        if(name.isEmpty()){
            throw new EmptyFieldsException();
        } else if(!isValidLength(name, 45)) {
            throw new InvalidUserInfoException();
        }
    }

    public String getSurname() {
        return personalInfo.surname();
    }

    private void checkSurname(String surname) throws InvalidUserInfoException, EmptyFieldsException {
        if(surname.isEmpty()){
            throw new EmptyFieldsException();
        } else if(!isValidLength(surname, 45)){
            throw new InvalidUserInfoException();
        }
    }

    public String getFiscalCode() {
        return personalInfo.fc();
    }

    private void checkFc(String fc) throws InvalidFiscalCodeException, EmptyFieldsException {
        if(fc.isEmpty()){
            throw new EmptyFieldsException();
        } else if(!isValidFc(fc)) {
            throw new InvalidFiscalCodeException();
        }
    }

    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[\\dLMNPQRSTUV]{2}[ABCDEHLMPRST][\\dLMNPQRSTUV]{2}[A-Z][\\dLMNPQRSTUV]{3}[A-Z])$|(\\d{11})$",fc);
    }

    public LocalDate getBirth() {
        return personalInfo.dateOfBirth();
    }

    private LocalDate checkBirth(String birth) throws InvalidBirthException, EmptyFieldsException {
        if(birth.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidBirth(birth)) {
            return LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }else {
            throw new InvalidBirthException();
        }
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
        return personalInfo.gender();
    }

    private void checkGender(char gender) {
        return;
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
