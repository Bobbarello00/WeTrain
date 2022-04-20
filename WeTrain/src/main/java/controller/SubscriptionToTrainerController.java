package controller;

import database.dao_classes.TrainerDAO;
import model.Trainer;
import model.User;
import viewone.bean.IdBean;
import viewone.bean.TrainerBean;
import viewone.bean.TrainerSearchBean;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionToTrainerController {

    private SubscriptionToTrainerController() {}

    public UserBean getTrainerUser(IdBean idBean) throws SQLException {
        Trainer trainer = new TrainerDAO().loadTrainer(String.valueOf(idBean.getId()));
        UserBean userBean = new UserBean(
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
    }

    private static class SingletonManager {
        private static final SubscriptionToTrainerController INSTANCE = new SubscriptionToTrainerController();
    }

    public static SubscriptionToTrainerController getInstance() {
        return SubscriptionToTrainerController.SingletonManager.INSTANCE;
    }

    public List<UserBean> setToUserBean(List<Trainer> trainerList) {
        List<UserBean> beanList = new ArrayList<>();
        for(User trainer : trainerList) {
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

    public List<UserBean> searchTrainers(TrainerSearchBean bean) throws SQLException {
        List<Trainer> trainerList = new TrainerDAO().searchTrainers(bean.getName());
        return setToUserBean(trainerList);
    }

    public List<UserBean> getTrainersList() throws SQLException{
        List<Trainer> trainerList = new TrainerDAO().loadAllTrainers();
        return setToUserBean(trainerList);
    }

}
