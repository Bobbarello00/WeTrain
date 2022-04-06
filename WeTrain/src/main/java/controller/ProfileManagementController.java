package controller;

import database.dao_classes.AthleteDAO;
import model.Athlete;
import model.LoggedUserSingleton;
import viewone.bean.CardInfoBean;

import java.sql.SQLException;

public class ProfileManagementController {

    public static void updateAthleteCardInfo(CardInfoBean cardInfoBean) throws SQLException {
        AthleteDAO athleteDAO = new AthleteDAO();
        Athlete athlete = athleteDAO.loadAthlete(LoggedUserSingleton.getInstance().getFiscalCode());
        athleteDAO.updateCardInfo(cardInfoBean.getCardNumber(), cardInfoBean.getExpirationDate(), athlete);
        LoggedUserSingleton.setInstance(athlete);
    }

}
