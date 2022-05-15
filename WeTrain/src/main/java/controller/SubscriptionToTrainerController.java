package controller;

import boundary.PaypalBoundary;
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
import java.util.Objects;

public class SubscriptionToTrainerController {

    private static final float SUBSCRIPTION_FEE = 5;

    private final LoginController loginController = new LoginController();
    private final NotificationsController notificationsController = new NotificationsController();
    private final PaypalBoundary paypalBoundary = new PaypalBoundary();

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

    public void subscribeToTrainer(String trainerFc) throws SQLException, DBUnreachableException, PaymentFailedException {
        Trainer trainer = new TrainerDAO().loadTrainer(trainerFc);
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        new AthleteDAO().setTrainer(athlete, trainerFc);
        try {
            paypalBoundary.pay(trainer.getIban(), athlete.getCardNumber(), athlete.getCardExpirationDate(), SUBSCRIPTION_FEE);
        }catch (PaymentFailedException e){
            new AthleteDAO().removeTrainer(athlete);
            throw new PaymentFailedException();
        }
        notificationsController.sendSubscriptionToTrainerNotification(trainer);
    }

    public void unsubscribeFromTrainer() throws SQLException, DBUnreachableException {
        AthleteDAO athleteDAO = new AthleteDAO();
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        athleteDAO.removeTrainer(athlete);
        if(!Objects.equals(athlete.getWorkoutPlan(), null)) {
            athleteDAO.removeWorkoutPlan(athlete.getWorkoutPlan().getId());
        }
    }

}
