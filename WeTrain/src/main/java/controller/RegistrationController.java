package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import model.Athlete;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;
import engeneering.LoggedUserSingleton;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {

    private final LoginController loginController = new LoginController();

    public void processUserInfo(UserBean bean) throws SQLException, DBUnreachableException, UserNotFoundException {
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
