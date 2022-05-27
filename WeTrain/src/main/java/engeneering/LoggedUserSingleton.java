package engeneering;

import controller.LoginController;
import exception.DBUnreachableException;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.PageSwitchSizeChange;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LoggedUserSingleton {

    private static String fc;
    private static UserInfoCarrier userInfoCarrier;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton() {}

    public static String getFc() {
        return fc;
    }

    public static void setFc(String fc) {
        LoggedUserSingleton.fc = fc;
    }

    public static UserInfoCarrier getUserInfo() throws SQLException, DBUnreachableException {
        if(userInfoCarrier == null){
            UserBean userBean = getInstance();
            userInfoCarrier = new UserInfoCarrier(
                    Objects.requireNonNull(userBean).getUsername(),
                    userBean.getType(),
                    userBean.getFiscalCode(),
                    userBean.getGender());
        }
        return userInfoCarrier;
    }

    public static UserBean getInstance() {
        User usr;
        try{
            usr = loginController.getLoggedUser();
            if (usr instanceof Athlete) {
                return new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                ((Athlete) usr).getCardNumber(),
                                ((Athlete) usr).getCardExpirationDate()
                        ));
            } else {
                return new TrainerBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        ((Trainer) usr).getIban());
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
        return null;
    }

    public static void resetUserInfo() {
        userInfoCarrier = null;
    }
}
