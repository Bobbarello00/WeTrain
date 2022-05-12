package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.DBUnreachableException;
import exception.invalid_data_exception.ExpiredCardException;
import model.Athlete;
import model.Trainer;
import viewone.bean.CardInfoBean;
import viewone.bean.IbanBean;

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
        new AthleteDAO().updateCardInfo(cardInfoBean.getCardNumber(),cardInfoBean.getExpirationDate(), athlete);
    }

    public void removeCardInfo(Athlete athlete) throws SQLException, DBUnreachableException {
        new AthleteDAO().removeCardInfo(athlete.getFiscalCode());
    }
}
