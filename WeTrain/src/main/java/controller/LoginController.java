package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import viewone.engeneering.FatalErrorManager;
import model.User;
import viewone.bean.CredentialsBean;

import java.sql.SQLException;

public class LoginController {

    private static String fc;

    private LoginController() {}

    private static class SingletonManager {
        private static final LoginController INSTANCE = new LoginController();
    }

    public static LoginController getInstance() {
        return LoginController.SingletonManager.INSTANCE;
    }

    public User getLoggedUser() throws SQLException {
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
        if(user != null){
            fc = user.getFiscalCode();
        }
        FatalErrorManager.kill();
    }
}
