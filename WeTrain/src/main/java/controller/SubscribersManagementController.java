package controller;

import database.dao_classes.TrainerDAO;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Trainer;
import model.User;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersManagementController {

    public List<UserBean> getSubscriberList(String trainerFc) throws SQLException, DBConnectionFailedException {
        List<Athlete> subscriberList = new TrainerDAO().loadAllTrainerSubscribers(trainerFc);
        return setToUserBean(subscriberList);
    }

    private List<UserBean> setToUserBean(List<Athlete> subscriberList) {
        List<UserBean> beanList = new ArrayList<>();
        for (User subscriber : subscriberList) {
            beanList.add(new UserBean(
                            subscriber.getUsername(),
                            subscriber.getName(),
                            subscriber.getSurname(),
                            subscriber.getFiscalCode(),
                            subscriber.getDateOfBirth(),
                            "Athlete",
                            subscriber.getGender(),
                            subscriber.getEmail(),
                            subscriber.getPassword()
                    )
            );
        }
        return beanList;
    }
}
