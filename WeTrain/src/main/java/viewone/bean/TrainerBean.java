package viewone.bean;

import model.Trainer;

import java.time.LocalDate;
import java.time.YearMonth;

public class TrainerBean extends UserBean{
    private final IbanBean ibanBean;

    public TrainerBean(String username, String name, String surname, String fc, LocalDate birth, String usrType, char gender, String email, String password, String iban) {
        super(username, name, surname, fc, birth, usrType, gender, email, password);
        ibanBean = new IbanBean(iban);
    }

    public String getIban() {
        return ibanBean.getIban();
    }
}