package controller;

import database.dao_classes.AthleteDAO;
import exception.ExpiredCardException;
import model.Athlete;
import viewone.bean.CardInfoBean;

import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileManagementController {

    private final LoginController loginController = LoginController.getInstance();

    private ProfileManagementController() {}

    private static class SingletonManager {
        private static final ProfileManagementController INSTANCE = new ProfileManagementController();
    }

    public static ProfileManagementController getInstance() {
        return ProfileManagementController.SingletonManager.INSTANCE;
    }

    public void updateAthleteCardInfo(CardInfoBean cardInfoBean) throws SQLException, ExpiredCardException {
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        if((cardInfoBean.getExpirationDate().getYear() < LocalDate.now().getYear()) ||
                ((cardInfoBean.getExpirationDate().getYear() == LocalDate.now().getYear()) &&
                        (cardInfoBean.getExpirationDate().getMonthValue() < LocalDate.now().getMonthValue()))) {

            throw new ExpiredCardException();
        }
        new AthleteDAO().updateCardInfo(cardInfoBean.getCardNumber(),cardInfoBean.getExpirationDate(), athlete);
    }

}
