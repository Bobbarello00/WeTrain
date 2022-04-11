package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exception.FatalErrorManager;
import model.User;
import viewone.bean.CredentialsBean;

import java.sql.SQLException;

public class LoginController {

    private static String fc;

    public static User getLoggedUser() throws SQLException {
        User user = new AthleteDAO().loadAthlete(fc);
        if(user == null){
            user = new TrainerDAO().loadTrainer(fc);
        }
        if(user == null){
            System.out.println("Error - Logged User is null.");
        }
        return user;
    }

    public static void login(CredentialsBean credentials) throws SQLException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        if(user == null){
            FatalErrorManager.kill();
        }
        assert user != null;
        fc = user.getFiscalCode();
    }
}
