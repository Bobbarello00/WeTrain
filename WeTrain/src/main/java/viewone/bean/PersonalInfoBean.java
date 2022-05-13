package viewone.bean;

import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.InvalidBirthException;
import exception.invalid_data_exception.InvalidFiscalCodeException;
import exception.invalid_data_exception.InvalidUserInfoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class PersonalInfoBean {
    private final String name;
    private final String surname;
    private final LocalDate dateOfBirth;
    private final String fc;
    private final char gender;

    public PersonalInfoBean(String name, String surname, LocalDate dateOfBirth, String fc, char gender) {
        /*This is a constructor without syntax check and is used by controller*/
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.fc = fc;
        this.gender = gender;
    }

    public PersonalInfoBean(String name, String surname, String dateOfBirth, String fc, char gender) throws InvalidUserInfoException, InvalidFiscalCodeException, EmptyFieldsException, InvalidBirthException {
        /*This is a constructor with syntax check and is used by view*/
        checkValue(name, surname, fc);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = checkBirth(dateOfBirth);
        this.fc = fc;
        this.gender = gender;
    }

    private void checkValue(String name, String surname, String fiscalCode) throws InvalidUserInfoException, InvalidFiscalCodeException, EmptyFieldsException {
        checkName(name);
        checkSurname(surname);
        checkFc(fiscalCode);
    }

    private void checkName(String name) throws InvalidUserInfoException, EmptyFieldsException {
        if(name.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isNotValidLength(name)) {
            throw new InvalidUserInfoException();
        }
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

    private boolean isNotValidLength(String str) {
        return str.length() > 45;
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

    private void checkSurname(String surname) throws InvalidUserInfoException, EmptyFieldsException {
        if(surname.isEmpty()){
            throw new EmptyFieldsException();
        } else if(isNotValidLength(surname)){
            throw new InvalidUserInfoException();
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFc() {
        return fc;
    }

    public char getGender() {
        return gender;
    }
}
