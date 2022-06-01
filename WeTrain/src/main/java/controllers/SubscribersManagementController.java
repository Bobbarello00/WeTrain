package controllers;

import database.dao_classes.TrainerDAO;
import engeneering.LoggedUserSingleton;
import exceptions.DBUnreachableException;
import models.Athlete;
import models.User;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import beans.SubscribersNumberBean;
import beans.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersManagementController {

    public List<UserBean> getSubscriberList() throws SQLException, DBUnreachableException {
        List<Athlete> subscriberList = new TrainerDAO().loadAllTrainerSubscribers(LoggedUserSingleton.getFc());
        return setToUserBean(subscriberList);
    }

    public SubscribersNumberBean getSubscribersNumber() throws DBUnreachableException, SQLException {
        return new SubscribersNumberBean(
                new TrainerDAO().getNumberOfSubscribers(LoggedUserSingleton.getFc())
        );
    }

    private List<UserBean> setToUserBean(List<Athlete> subscriberList) {
        List<UserBean> beanList = new ArrayList<>();
        for (User subscriber : subscriberList) {
            beanList.add(new UserBean(
                            subscriber.getUsername(),
                            "Athlete",
                            new PersonalInfoBean(
                                    subscriber.getName(),
                                    subscriber.getSurname(),
                                    subscriber.getDateOfBirth(),
                                    subscriber.getFiscalCode(),
                                    subscriber.getGender()
                            ),
                            CredentialsBean.ctorWithoutSyntaxCheck(
                                    subscriber.getEmail(),
                                    subscriber.getPassword())
                    )
            );
        }
        return beanList;
    }
}
