package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.*;
import exception.invalid_data_exception.InvalidIbanException;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionToTrainerController {

    LoginController loginController = new LoginController();

    public TrainerBean getTrainer() throws SQLException, DBUnreachableException, InvalidIbanException {
        Trainer trainer = ((Athlete) loginController.getLoggedUser()).getTrainer();
        if(trainer != null){
            return new TrainerBean(
                    trainer.getUsername(),
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
                    ),
                    trainer.getIban());
        }
        return null;
    }

    public UserBean getTrainerUser(FcBean fcBean) throws SQLException, DBUnreachableException {
        Trainer trainer = new TrainerDAO().loadTrainer(fcBean.getFc());
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

    public List<UserBean> setToUserBean(List<Trainer> trainerList) {
        List<UserBean> beanList = new ArrayList<>();
        for (User trainer : trainerList) {
            beanList.add(new UserBean(
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
                    ))
            );
        }
        return beanList;
    }

    public List<UserBean> searchTrainers(TrainerSearchBean bean) throws SQLException, DBUnreachableException {
        List<Trainer> trainerList = new TrainerDAO().searchTrainers(bean.getName());
        return setToUserBean(trainerList);
    }

    public List<UserBean> getTrainersList() throws SQLException, DBUnreachableException {
        List<Trainer> trainerList = new TrainerDAO().loadAllTrainers();
        return setToUserBean(trainerList);
    }

    public void subscribeToTrainer(String fc) throws SQLException, DBUnreachableException {
        new AthleteDAO().setTrainer((Athlete) loginController.getLoggedUser(), fc);
    }

    public void unsubscribeFromTrainer() throws SQLException, DBUnreachableException {
        AthleteDAO athleteDAO = new AthleteDAO();
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        athleteDAO.removeTrainer(athlete);
        athleteDAO.removeWorkoutPlan(athlete.getWorkoutPlan().getId());
    }

}
