package viewone.bean;

import exception.InvalidCardInfoException;
import viewone.engeneering.FatalCaseManager;

import java.time.LocalDate;
import java.time.YearMonth;

public class AthleteBean extends UserBean{
    private CardInfoBean cardInfoBean = null;

    public AthleteBean(String username, String name, String surname, String fc, LocalDate birth,  char gender, String email, String password, String cardNumber, YearMonth cardExpirationDate) throws InvalidCardInfoException {
        super(username, name, surname, fc, birth, "Athlete", gender, email, password);
        if(cardNumber == null & cardExpirationDate == null) {
            cardInfoBean = new CardInfoBean(null, null);
        } else if(cardNumber == null | cardExpirationDate == null) {
            FatalCaseManager.killApplication();
        } else {
            cardInfoBean = new CardInfoBean(cardNumber, cardExpirationDate);
        }
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
