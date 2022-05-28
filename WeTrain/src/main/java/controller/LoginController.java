package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import exception.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import model.User;
import engeneering.LoggedUserSingleton;
import viewone.bean.CredentialsBean;
import viewone.bean.UserBean;

import java.sql.SQLException;

public class LoginController {

    public User getLoggedUser() throws SQLException, DBUnreachableException {
        User user = new AthleteDAO().loadAthlete(LoggedUserSingleton.getFc());
        if(user == null){
            user = new TrainerDAO().loadTrainer(LoggedUserSingleton.getFc());
        }
        if(user == null){
            throw new IsNeitherATrainerNorAnAthleteException();
        }
        return user;
    }

    public UserBean login(CredentialsBean credentials) throws SQLException, DBUnreachableException, UserNotFoundException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.getUserBean(user);
    }
}
