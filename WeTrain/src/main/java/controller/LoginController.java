package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import exception.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import model.User;
import viewone.bean.CredentialsBean;
import viewone.LoggedUserSingleton;
import viewone.bean.PersonalInfoBean;
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

    public void login(CredentialsBean credentials) throws SQLException, DBUnreachableException, UserNotFoundException {
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return new UserBean(
                trainer.getUsername(),
                "Trainer",
                new PersonalInfoBean(
                        trainer.getName(),
                        trainer.getSurname(),
                        trainer.getDateOfBirth(),
                        trainer.getFiscalCode(),
                        trainer.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        trainer.getEmail(),
                        trainer.getPassword()
                ));
    }
}
