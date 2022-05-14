package viewone.engeneering;

import controller.LoginController;
import exception.*;
import exception.invalid_data_exception.InvalidIbanException;
import exception.runtime_exception.FatalErrorException;
import model.Athlete;
import model.Trainer;
import model.User;
import model.record.PersonalInfo;
import viewone.bean.*;

import java.sql.SQLException;
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

    public static UserInfoCarrier getUserInfo() throws SQLException, DBUnreachableException, InvalidIbanException {
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

    public static UserBean getInstance() throws SQLException, DBUnreachableException {
        User usr = loginController.getLoggedUser();

        if (usr instanceof Athlete) {
            return new AthleteBean(
                    usr.getUsername(),
                    new PersonalInfoBean(usr.getName(),
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
    }

    public static void resetUserInfo() {
        userInfoCarrier = null;
    }
}
