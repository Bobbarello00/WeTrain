package viewone.engeneering;

import controller.LoginController;
import exception.*;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.bean.AthleteBean;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;

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

    public static UserInfoCarrier getUserInfo() throws SQLException, DBConnectionFailedException {
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

    public static UserBean getInstance() throws SQLException, DBConnectionFailedException {
        User usr = loginController.getLoggedUser();

        if (usr instanceof Athlete) {
            return new AthleteBean(usr.getUsername(),
                    usr.getName(),
                    usr.getSurname(),
                    usr.getFiscalCode(),
                    usr.getDateOfBirth(),
                    usr.getGender(),
                    usr.getEmail(),
                    usr.getPassword(),
                    ((Athlete) usr).getCardNumber(),
                    ((Athlete) usr).getCardExpirationDate());
        } else if (usr instanceof Trainer) {
            return new TrainerBean(usr.getUsername(),
                    usr.getName(),
                    usr.getSurname(),
                    usr.getFiscalCode(),
                    usr.getDateOfBirth(),
                    usr.getGender(),
                    usr.getEmail(),
                    usr.getPassword(),
                    ((Trainer) usr).getIban());
        } else {
            System.out.println("User in LoggedUserSingleton is neither an Athlete nor Trainer.");
            FatalCaseManager.killApplication();
            return null;
        }
    }

    public static void resetUserInfo() {
        userInfoCarrier = null;
    }
}
