package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import model.Athlete;
import model.LoggedUserSingleton;
import model.Trainer;
import viewone.bean.CredentialsBean;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {
    private static CredentialsBean credentialsBean;

    public static void processUserInfo(UserBean bean) throws SQLException {
        if (Objects.equals(bean.getType(), "Athlete")) {
            AthleteDAO athleteDAO = new AthleteDAO();
            Athlete athlete = new Athlete(bean.getName(), bean.getSurname(), bean.getUsername(), bean.getBirth(), bean.getFiscalCode(), bean.getGender(), credentialsBean.getEmail(), credentialsBean.getPassword());
            athleteDAO.saveAthlete(athlete);
        } else {
            TrainerDAO trainerDAO = new TrainerDAO();
            Trainer trainer = new Trainer(bean.getName(), bean.getSurname(), bean.getUsername(), bean.getBirth(), bean.getFiscalCode(), bean.getGender(), credentialsBean.getEmail(), credentialsBean.getPassword());
            trainerDAO.saveTrainer(trainer);
        }
        LoginController.login(credentialsBean);
    }

    public static void setCredentialInfo(CredentialsBean credential) {
        credentialsBean = credential;
    }
}
