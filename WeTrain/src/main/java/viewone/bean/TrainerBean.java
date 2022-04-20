package viewone.bean;

import exception.InvalidCredentialsException;
import exception.InvalidFiscalCodeException;
import exception.InvalidUserInfoException;

import java.time.LocalDate;

public class TrainerBean extends UserBean{
    private final IbanBean ibanBean;

    public TrainerBean(String username, String name, String surname, String fc, LocalDate birth, char gender, String email, String password, String iban) throws InvalidUserInfoException, InvalidCredentialsException, InvalidFiscalCodeException {
        super(username, name, surname, fc, birth, "Trainer", gender, email, password);
        ibanBean = new IbanBean(iban);
    }

    public String getIban() {
        return ibanBean.getIban();
    }
}