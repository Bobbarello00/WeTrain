package controllers;

import exceptions.invalid_data_exception.NoCardInsertedException;
import viewone.beans.PaymentBean;
import boundaries.PaypalSystemBoundary;
import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exceptions.*;
import exceptions.invalid_data_exception.InvalidIbanException;
import models.Athlete;
import models.Trainer;
import models.User;
import viewone.beans.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscribeToTrainerController {

    private static final float SUBSCRIPTION_FEE = 5;

    private final LoginController loginController = new LoginController();
    private final NotificationsController notificationsController = new NotificationsController();
    private final PaypalSystemBoundary paypalSystemBoundary = new PaypalSystemBoundary();

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

    public List<UserBean> searchTrainers(SearchBean bean) throws SQLException, DBUnreachableException {
        List<Trainer> trainerList = new TrainerDAO().searchTrainers(bean.getName());
        return setToUserBean(trainerList);
    }

    public List<UserBean> getTrainersList() throws SQLException, DBUnreachableException {
        List<Trainer> trainerList = new TrainerDAO().loadAllTrainers();
        return setToUserBean(trainerList);
    }

    public void subscribeToTrainer(String trainerFc) throws SQLException, DBUnreachableException, PaymentFailedException, AlreadySubscribedException, NoCardInsertedException {
        Trainer trainer = new TrainerDAO().loadTrainer(trainerFc);
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        if(Objects.equals(athlete.getTrainer().getFiscalCode(), trainerFc)) {
            throw new AlreadySubscribedException();
        }
        new AthleteDAO().setTrainer(athlete, trainerFc);
        try {
            paypalSystemBoundary.pay(new PaymentBean(trainer.getIban(), athlete.getCardNumber(), athlete.getCardExpirationDate(), SUBSCRIPTION_FEE));
        }catch (PaymentFailedException e){
            new AthleteDAO().removeTrainer(athlete);
            throw new PaymentFailedException();
        } catch (NoCardInsertedException e) {
            new AthleteDAO().removeTrainer(athlete);
            throw new NoCardInsertedException();
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
