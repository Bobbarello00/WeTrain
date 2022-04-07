package model;

import controller.LoginController;
import viewone.bean.AthleteBean;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;

import java.sql.SQLException;

public class LoggedUserSingleton {

    private static String fiscalCode;

    private LoggedUserSingleton() {}

    //TODO Creare un controller che comunica con la DAO?
    public static UserBean getInstance() throws SQLException {
        User usr = LoginController.getLoggedUser();
        if(usr instanceof Athlete) {
            return new AthleteBean(usr.getUsername(), usr.getName(), usr.getSurname(), usr.getFiscalCode(), usr.getDateOfBirth(), "Athlete", usr.getGender(), usr.getEmail(), usr.getPassword(), ((Athlete)usr).getCardNumber(), ((Athlete) usr).getCardExpirationDate());
        } else if(usr instanceof Trainer) {
            return new TrainerBean(usr.getUsername(), usr.getName(), usr.getSurname(), usr.getFiscalCode(), usr.getDateOfBirth(), "Trainer", usr.getGender(), usr.getEmail(), usr.getPassword(), ((Trainer) usr).getIban());
        } else {
            //throw new ;
            System.out.println("User in LoggedUserSingleton is neither an Athlete nor Trainer.");
            return null;
        }
    }
}
