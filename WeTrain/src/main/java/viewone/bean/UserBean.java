package viewone.bean;

import exception.invalidDataException.*;

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

    public UserBean(String username, String name, String surname, String fiscalCode, String birth, String type, char gender, String email, String password) throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException {
        /*This is a constructor with syntax check and is used by view*/
        setUser(username, name, surname, fiscalCode, type, gender);
        setBirth(birth);
        credentials = CredentialsBean.ctorWithSyntaxCheck(email, password);
    }

    private void setUser(String username, String name, String surname, String fiscalCode, String type, char gender) throws InvalidUserInfoException, InvalidFiscalCodeException, EmptyFieldsException {
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

    public void setUsername(String username) throws InvalidUserInfoException, EmptyFieldsException {
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
        return name;
    }

    public void setName(String name) throws InvalidUserInfoException, EmptyFieldsException {
        if(name.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidLength(name, 45)){
            this.name = name;
        }else {
            throw new InvalidUserInfoException();
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidUserInfoException, EmptyFieldsException {
        if(surname.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidLength(surname, 45)){
            this.surname = surname;
        }else {
            throw new InvalidUserInfoException();
        }
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFc(String fc) throws InvalidFiscalCodeException, EmptyFieldsException {
        if(fc.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidFc(fc)) {
            this.fiscalCode = fc;
        }else {
            throw new InvalidFiscalCodeException();
        }
    }

    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[\\dLMNPQRSTUV]{2}[ABCDEHLMPRST][\\dLMNPQRSTUV]{2}[A-Z][\\dLMNPQRSTUV]{3}[A-Z])$|(\\d{11})$",fc);
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(String birth) throws InvalidBirthException, EmptyFieldsException {
        if(birth.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isValidBirth(birth)) {
            this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
