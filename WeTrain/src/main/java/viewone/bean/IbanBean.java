package viewone.bean;

import exception.InvalidIbanException;

import java.util.regex.Pattern;

public class IbanBean {
    private String iban;

    private IbanBean(){}

    public static IbanBean ctorWithSyntaxCheck(String iban) throws InvalidIbanException {
        /*This is a constructor with syntax check and is used by view*/
        IbanBean ibanBean = new IbanBean();
        ibanBean.setIban(iban);
        return ibanBean;
    }

    public static IbanBean ctorWithoutSyntaxCheck(String iban) {
        /*This is a constructor without syntax check and is used by controller*/
        IbanBean ibanBean = new IbanBean();
        ibanBean.iban = iban;
        return ibanBean;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) throws InvalidIbanException {
        iban = iban.replace(" ","");
        iban = iban.replace("-","");
        iban = iban.toUpperCase();
        if(checkAndReturnValidIban(iban)){
            this.iban = iban.toUpperCase();
        }else{
            throw new InvalidIbanException();
        }
    }

    private boolean checkAndReturnValidIban(String iban) {
        return Pattern.matches("^([A-Z]{2}[ \\-]?\\d{2})(?=(?:[ \\-]?[A-Z\\d]){9,30}$)((?:[ \\-]?[A-Z\\d]{3,5}){2,7})([ \\-]?[A-Z\\d]{1,3})?$", iban);
    }
}
