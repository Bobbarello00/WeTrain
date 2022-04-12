package viewone.bean;

import java.time.LocalDate;
import java.time.YearMonth;

public class AthleteBean extends UserBean{
    private final CardInfoBean cardInfoBean;

    public AthleteBean(String username, String name, String surname, String fc, LocalDate birth, String usrType, char gender, String email, String password, String cardNumber, YearMonth cardExpirationDate) {
        super(username, name, surname, fc, birth, usrType, gender, email, password);
        cardInfoBean = new CardInfoBean(cardNumber, cardExpirationDate);
    }

    public void setCardType(String cardType){
        cardInfoBean.setType(cardType);
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
