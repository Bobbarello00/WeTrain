package controller;

import database.dao_classes.UserDAO;
import model.Athlete;
import model.LoggedUserSingleton;
import model.Trainer;
import model.User;
import viewone.bean.CredentialsBean;

import java.sql.SQLException;

public class LoginController {

    public static void login(CredentialsBean credentials) throws SQLException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        if(user == null) {
            //TODO EXCEPTION user non trovato
            System.out.println("EXCEPTION user non trovato");
        }
        if(user instanceof Athlete){
            LoggedUserSingleton.setInstance((Athlete) user);
        } else {
            LoggedUserSingleton.setInstance((Trainer) user);
        }
    }
}
