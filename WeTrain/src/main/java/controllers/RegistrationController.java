package controllers;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import models.Athlete;
import models.Trainer;
import models.record.Credentials;
import models.record.PersonalInfo;
import engeneering.LoggedUserSingleton;
import beans.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {

    private final LoginController loginController = new LoginController();

    public void processUserInfo(UserBean bean) throws SQLException, DBUnreachableException, UserNotFoundException{
        if (Objects.equals(bean.getType(), "Athlete")) {
            AthleteDAO athleteDAO = new AthleteDAO();
            Athlete athlete = new Athlete(
                    bean.getUsername(),
                    new PersonalInfo(
                        bean.getName(),
                        bean.getSurname(),
                        bean.getBirth(),
                        bean.getFiscalCode(),
                        bean.getGender()
                    ),
                    new Credentials(
                            bean.getEmail(),
                            bean.getPassword()
                    )
            );
            athleteDAO.saveAthlete(athlete);
        } else {
            TrainerDAO trainerDAO = new TrainerDAO();
            Trainer trainer = new Trainer(
                    bean.getUsername(),
                    new PersonalInfo(
                            bean.getName(),
                            bean.getSurname(),
                            bean.getBirth(),
                            bean.getFiscalCode(),
                            bean.getGender()
                    ),
                    new Credentials(
                            bean.getEmail(),
                            bean.getPassword()
                    )
            );
            trainerDAO.saveTrainer(trainer);
        }
        LoggedUserSingleton.resetUserInfo();
        loginController.login(bean.getCredentials());
        LoggedUserSingleton.setFc(bean.getFiscalCode());
    }
}
