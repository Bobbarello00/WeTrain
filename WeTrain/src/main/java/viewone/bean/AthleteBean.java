package viewone.bean;

import model.record.PersonalInfo;

import java.time.LocalDate;
import java.time.YearMonth;

public class AthleteBean extends UserBean{
    private final CardInfoBean cardInfoBean;

    public AthleteBean(String username, PersonalInfo personalInfo, CardInfoBean cardInfoBean) {
        super(username, personalInfo, "Athlete", cardInfoBean);
        this.cardInfoBean = cardInfoBean;
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
