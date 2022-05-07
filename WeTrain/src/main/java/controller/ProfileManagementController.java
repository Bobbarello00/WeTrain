package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import exception.DBConnectionFailedException;
import exception.invalidDataException.ExpiredCardException;
import model.Athlete;
import model.Trainer;
import viewone.bean.CardInfoBean;
import viewone.bean.IbanBean;

import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileManagementController {

    private final LoginController loginController = new LoginController();

    public void updateTrainerIban(IbanBean ibanBean) throws SQLException, DBConnectionFailedException {
        Trainer trainer = (Trainer) loginController.getLoggedUser();
        new TrainerDAO().updateIban(ibanBean.getIban(), trainer);

    }

    public void updateAthleteCardInfo(CardInfoBean cardInfoBean) throws SQLException, ExpiredCardException, DBConnectionFailedException {
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        if((cardInfoBean.getExpirationDate().getYear() < LocalDate.now().getYear()) ||
                ((cardInfoBean.getExpirationDate().getYear() == LocalDate.now().getYear()) &&
                        (cardInfoBean.getExpirationDate().getMonthValue() < LocalDate.now().getMonthValue()))) {

            throw new ExpiredCardException();
        }
        new AthleteDAO().updateCardInfo(cardInfoBean.getCardNumber(),cardInfoBean.getExpirationDate(), athlete);
    }

    public void removeCardInfo(Athlete athlete) throws SQLException, DBConnectionFailedException {
        new AthleteDAO().removeCardInfo(athlete.getFiscalCode());
    }
}
