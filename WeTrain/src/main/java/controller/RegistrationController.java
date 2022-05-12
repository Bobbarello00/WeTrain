package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.DBUnreachableException;
import exception.invalid_data_exception.InvalidCredentialsException;
import model.Athlete;
import model.Trainer;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {

    private final LoginController loginController = new LoginController();

    public void processUserInfo(UserBean bean) throws SQLException, InvalidCredentialsException, DBUnreachableException {
        if (Objects.equals(bean.getType(), "Athlete")) {
            AthleteDAO athleteDAO = new AthleteDAO();
            Athlete athlete = new Athlete(
                    bean.getName(),
                    bean.getSurname(),
                    bean.getUsername(),
                    bean.getBirth(),
                    bean.getFiscalCode(),
                    bean.getGender(),
                    bean.getEmail(),
                    bean.getPassword());
            athleteDAO.saveAthlete(athlete);
        } else {
            TrainerDAO trainerDAO = new TrainerDAO();
            Trainer trainer = new Trainer(
                    bean.getName(),
                    bean.getSurname(),
                    bean.getUsername(),
                    bean.getBirth(),
                    bean.getFiscalCode(),
                    bean.getGender(),
                    bean.getEmail(),
                    bean.getPassword());
            trainerDAO.saveTrainer(trainer);
        }
        loginController.login(bean.getCredentials());
    }
}
