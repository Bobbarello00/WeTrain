package controller;

import database.dao_classes.UserDAO;
import exception.ElementNotFoundException;
import model.LoggedUserSingleton;
import model.User;
import viewone.bean.CredentialsBean;

import java.sql.SQLException;

public class LoginController {

    public static void login(CredentialsBean credentials) throws SQLException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        LoggedUserSingleton.setInstance(user);
    }
}
