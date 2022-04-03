package controller;

import database.dao_classes.UserDAO;
import exception.ElementNotFoundException;
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
            throw new ElementNotFoundException();

        }
        LoggedUserSingleton.setInstance(user);
    }
}
