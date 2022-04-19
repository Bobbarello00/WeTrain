package controller;

import database.dao_classes.TrainerDAO;
import model.User;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionToTrainerController {

    private SubscriptionToTrainerController() {}

    private static class SingletonManager {
        private static final SubscriptionToTrainerController INSTANCE = new SubscriptionToTrainerController();
    }

    public static SubscriptionToTrainerController getInstance() {
        return SubscriptionToTrainerController.SingletonManager.INSTANCE;
    }


    public List<UserBean> getTrainersList() throws SQLException{
        List<User> trainersList = new TrainerDAO().loadAllTrainers();
        List<UserBean> beanList = new ArrayList<>();
        for(User trainer : trainersList) {
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
    }

}
