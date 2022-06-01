package controllers;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.ExpiredCardException;
import models.Athlete;
import models.Trainer;
import models.User;
import models.record.Card;
import beans.CardInfoBean;
import beans.IbanBean;

import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileManagementController {

    private final LoginController loginController = new LoginController();

    public void updateTrainerIban(IbanBean ibanBean) throws SQLException, DBUnreachableException {
        Trainer trainer = (Trainer) loginController.getLoggedUser();
        new TrainerDAO().updateIban(ibanBean.getIban(), trainer);

    }

    public void updateAthleteCardInfo(CardInfoBean cardInfoBean) throws SQLException, ExpiredCardException, DBUnreachableException {
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        if((cardInfoBean.getExpirationDate().getYear() < LocalDate.now().getYear()) ||
                ((cardInfoBean.getExpirationDate().getYear() == LocalDate.now().getYear()) &&
                        (cardInfoBean.getExpirationDate().getMonthValue() < LocalDate.now().getMonthValue()))) {

            throw new ExpiredCardException();
        }
        new AthleteDAO().updateCardInfo(
                new Card(
                        cardInfoBean.getCardNumber(),
                        cardInfoBean.getExpirationDate()
                ),
                athlete
        );
    }

    public void deleteUser() throws DBUnreachableException, SQLException {
        User user = loginController.getLoggedUser();
        new UserDAO().deleteUser(user);
    }
}
