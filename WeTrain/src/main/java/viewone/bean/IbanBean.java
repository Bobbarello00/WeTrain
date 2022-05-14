package viewone.bean;

import exception.invalid_data_exception.InvalidIbanException;

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
        return Pattern.matches("^(it|IT)\\d{2}[A-Za-z]\\d{10}[\\dA-Za-z]{12}$", iban);
    }
}
