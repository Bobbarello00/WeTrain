package controller;

import database.dao_classes.AthleteDAO;
import model.Athlete;
import model.LoggedUserSingleton;
import viewone.bean.CardInfoBean;

import java.sql.SQLException;

public class ProfileManagementController {

    public static void updateAthleteCardInfo(CardInfoBean cardInfoBean) throws SQLException {
        Athlete athlete = (Athlete) LoggedUserSingleton.getInstance();
        new AthleteDAO().updateCardInfo(cardInfoBean.getCardNumber(),cardInfoBean.getExpirationDate(), athlete);
    }

}
