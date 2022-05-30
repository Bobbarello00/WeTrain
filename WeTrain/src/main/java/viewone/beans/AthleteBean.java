package viewone.beans;

import java.time.YearMonth;

public class AthleteBean extends UserBean{
    private final CardInfoBean cardInfoBean;

    public AthleteBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean, CardInfoBean cardInfoBean) {
        super(username, "Athlete", personalInfo, credentialsBean);
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
