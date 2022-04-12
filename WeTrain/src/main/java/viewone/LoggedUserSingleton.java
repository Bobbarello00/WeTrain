package viewone;

import controller.LoginController;
import exception.ExpiredCardException;
import engeneering.FatalErrorManager;
import exception.InvalidCardInfoException;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.bean.AthleteBean;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;

import java.sql.SQLException;

public class LoggedUserSingleton {

    private static final LoginController loginController = LoginController.getInstance();

    private LoggedUserSingleton() {}

    public static UserBean getInstance() throws SQLException, ExpiredCardException, InvalidCardInfoException {
        User usr = loginController.getLoggedUser();
        if(usr instanceof Athlete) {
            return new AthleteBean(usr.getUsername(), usr.getName(), usr.getSurname(), usr.getFiscalCode(), usr.getDateOfBirth(), "Athlete", usr.getGender(), usr.getEmail(), usr.getPassword(), ((Athlete)usr).getCardNumber(), ((Athlete) usr).getCardExpirationDate());
        } else if(usr instanceof Trainer) {
            return new TrainerBean(usr.getUsername(), usr.getName(), usr.getSurname(), usr.getFiscalCode(), usr.getDateOfBirth(), "Trainer", usr.getGender(), usr.getEmail(), usr.getPassword(), ((Trainer) usr).getIban());
        } else {
            System.out.println("User in LoggedUserSingleton is neither an Athlete nor Trainer.");
            FatalErrorManager.kill();
            return null;
        }
    }
}
