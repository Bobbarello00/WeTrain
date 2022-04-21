package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.*;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.bean.*;
import viewone.engeneering.FatalCaseManager;
import viewone.engeneering.LoggedUserSingleton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionToTrainerController {

    LoginController loginController = new LoginController();

    public UserBean getTrainerUser(FcBean fcBean) throws SQLException {
        Trainer trainer = new TrainerDAO().loadTrainer(fcBean.getFc());
        try {
            return new UserBean(
                    trainer.getUsername(),
                    trainer.getName(),
                    trainer.getSurname(),
                    trainer.getFiscalCode(),
                    trainer.getDateOfBirth(),
                    "Trainer",
                    trainer.getGender(),
                    trainer.getEmail(),
                    trainer.getPassword()
            );
        } catch (InvalidUserInfoException | InvalidFiscalCodeException | InvalidCredentialsException e) {
            FatalCaseManager.killApplication();
            return null;
        }
    }

    public List<UserBean> setToUserBean(List<Trainer> trainerList) {
        try {
            List<UserBean> beanList = new ArrayList<>();
            for (User trainer : trainerList) {
                beanList.add(new UserBean(
                                trainer.getUsername(),
                                trainer.getName(),
                                trainer.getSurname(),
                                trainer.getFiscalCode(),
                                trainer.getDateOfBirth(),
                                "Trainer",
                                trainer.getGender(),
                                trainer.getEmail(),
                                trainer.getPassword()
                        )
                );
            }
            return beanList;
        } catch (InvalidUserInfoException | InvalidFiscalCodeException | InvalidCredentialsException e) {
            FatalCaseManager.killApplication();
            return null;
        }
    }

    public List<UserBean> searchTrainers(TrainerSearchBean bean) throws SQLException {
        List<Trainer> trainerList = new TrainerDAO().searchTrainers(bean.getName());
        return setToUserBean(trainerList);
    }

    public List<UserBean> getTrainersList() throws SQLException{
        List<Trainer> trainerList = new TrainerDAO().loadAllTrainers();
        return setToUserBean(trainerList);
    }

    public void subscribeToTrainer(String fc) throws SQLException {
        new AthleteDAO().setTrainer((Athlete) loginController.getLoggedUser(), fc);
    }

}
