package viewone.bean;

public class TrainerBean extends UserBean{
    private final IbanBean ibanBean;

    public TrainerBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean, String iban) {
        super(username, "Trainer", personalInfo, credentialsBean);
        ibanBean = IbanBean.ctorWithoutSyntaxCheck(iban);
    }

    public String getIban() {
        return ibanBean.getIban();
    }
}