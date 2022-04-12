package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import model.Athlete;
import model.Trainer;
import viewone.bean.CredentialsBean;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {
    private CredentialsBean credentialsBean;

    private RegistrationController() {}

    private static class SingletonManager {
        private static final RegistrationController INSTANCE = new RegistrationController();
    }

    public static RegistrationController getInstance() {
        return SingletonManager.INSTANCE;
    }

    public void processUserInfo(UserBean bean) throws SQLException {
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

    public void setCredentialInfo(CredentialsBean credential) {
        credentialsBean = credential;
    }
}
