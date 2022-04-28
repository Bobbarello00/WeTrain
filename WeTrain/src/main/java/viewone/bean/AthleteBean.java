package viewone.bean;

import exception.InvalidCredentialsException;
import exception.InvalidFiscalCodeException;
import exception.InvalidUserInfoException;
import viewone.engeneering.FatalCaseManager;

import java.time.LocalDate;
import java.time.YearMonth;

public class AthleteBean extends UserBean{
    private final CardInfoBean cardInfoBean;

    public AthleteBean(String username, String name, String surname, String fc, LocalDate birth,  char gender, String email, String password, String cardNumber, YearMonth cardExpirationDate) {
        super(username, name, surname, fc, birth, "Athlete", gender, email, password);
        cardInfoBean = new CardInfoBean(cardNumber, cardExpirationDate);
    }

    public String getCardType() {
        return cardInfoBean.getType();
    }

    public String getCardNumber(){
        return cardInfoBean.getCardNumber();
    }

    public YearMonth getCardExpirationDate(){
        return cardInfoBean.getExpirationDate();
    }
}
