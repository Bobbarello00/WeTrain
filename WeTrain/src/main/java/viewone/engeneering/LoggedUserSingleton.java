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

public class LoggedUserSingleton {

    private static String fc;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton() {}

    public static String getFc() {
        return fc;
    }

    public static void setFc(String fc) {
        LoggedUserSingleton.fc = fc;
    }

    public static UserBean getInstance() throws SQLException, ExpiredCardException, InvalidCardInfoException, DBConnectionFailedException {
        User usr = loginController.getLoggedUser();
        try{
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
        } catch (InvalidUserInfoException | InvalidCredentialsException | InvalidFiscalCodeException e) {
            FatalCaseManager.killApplication();
            return null;
        }
    }
}
