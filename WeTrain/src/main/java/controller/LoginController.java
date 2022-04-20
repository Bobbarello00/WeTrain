package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import viewone.engeneering.FatalCaseManager;
import model.User;
import viewone.bean.CredentialsBean;
import viewone.engeneering.LoggedUserSingleton;

import java.sql.SQLException;

public class LoginController {

    public User getLoggedUser() throws SQLException {
        User user = new AthleteDAO().loadAthlete(LoggedUserSingleton.getFc());
        if(user == null){
            user = new TrainerDAO().loadTrainer(LoggedUserSingleton.getFc());
        }
        if(user == null){
            System.out.println("Error - Logged User is null.");
        }
        return user;
    }

    public static void login(CredentialsBean credentials) throws SQLException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        LoggedUserSingleton.setFc(user.getFiscalCode());
    }
}
