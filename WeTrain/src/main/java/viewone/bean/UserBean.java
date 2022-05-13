package viewone.bean;

import exception.invalid_data_exception.*;

import java.time.LocalDate;

public class UserBean {

    private String username;
    private final PersonalInfoBean personalInfo;
    private String type;
    private final CredentialsBean credentials;

    public UserBean(String username, String type, PersonalInfoBean personalInfo, CredentialsBean credentials) {
        /*This is a constructor without syntax check and is used by controller*/
        this.username = username;
        this.personalInfo = personalInfo;
        this.type = type;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public UserBean(String username, PersonalInfoBean personalInfo, String type, CredentialsBean credentials) throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException {
        /*This is a constructor with syntax check and is used by view*/
        setUsername(username);
        setType(type);
        this.personalInfo = personalInfo;
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
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
        } else if(isValidLength(username)){
            this.username = username;
        }else {
            throw new InvalidUserInfoException();
        }
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

    public String getName() {
        return personalInfo.getName();
    }

    public String getSurname() {
        return personalInfo.getSurname();
    }

    public String getFiscalCode() {
        return personalInfo.getFc();
    }

    public LocalDate getBirth() {
        return personalInfo.getDateOfBirth();
    }

    public char getGender() {
        return personalInfo.getGender();
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
