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
    private static CredentialsBean credentialsBean;

    public static void processUserInfo(UserBean bean) throws SQLException {

        if (Objects.equals(bean.getType(), "Athlete")) {
            AthleteDAO athlete = new AthleteDAO();
            athlete.saveAthlete(new Athlete(bean.getName(), bean.getSurname(), bean.getUsername(), bean.getBirth(), bean.getFc(), bean.getGender(), credentialsBean.getEmail(), credentialsBean.getPassword()));
        } else {
            TrainerDAO trainer = new TrainerDAO();
            trainer.saveTrainer(new Trainer(bean.getName(), bean.getSurname(), bean.getUsername(), bean.getBirth(), bean.getFc(), bean.getGender(), credentialsBean.getEmail(), credentialsBean.getPassword()));
        }
    }

    public static void setCredentialInfo(CredentialsBean credential) {
        credentialsBean = credential;
    }
}
