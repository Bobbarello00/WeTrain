package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import model.Athlete;
import model.Trainer;
import viewone.bean.CredentialBean;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {
    private static CredentialBean credentialBean;

    public static void processUserInfo(UserBean bean) throws SQLException {
        if(Objects.equals(bean.getType(), "Athlete")) {
            AthleteDAO athlete = new AthleteDAO();
            athlete.saveAthlete(new Athlete(bean.getName(), bean.getSurname(), bean.getBirth(), bean.getFc(), credentialBean.getEmail(), credentialBean.getPassword()));
        } else {
            TrainerDAO trainer = new TrainerDAO();
            trainer.saveTrainer(new Trainer(bean.getName(), bean.getSurname(), bean.getBirth(), bean.getFc(), credentialBean.getEmail(), credentialBean.getPassword()));
        }
    }

    public static void setCredentialInfo(CredentialBean credential) {
        credentialBean = credential;
    }

}
