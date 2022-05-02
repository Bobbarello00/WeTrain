package viewone.bean;

import exception.InvalidIbanException;

import java.util.regex.Pattern;

public class IbanBean {
    private String iban;

    public IbanBean(String iban) throws InvalidIbanException {
        setIban(iban);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) throws InvalidIbanException {
        if(checkAndReturnValidIban(iban)){
            this.iban = iban;
        }else{
            throw new InvalidIbanException();
        }
    }

    private boolean checkAndReturnValidIban(String iban) {
        return Pattern.matches("^([A-Z]{2}[ \\-]?\\d{2})(?=(?:[ \\-]?[A-Z\\d]){9,30}$)((?:[ \\-]?[A-Z\\d]{3,5}){2,7})([ \\-]?[A-Z\\d]{1,3})?$", iban);
    }
}
