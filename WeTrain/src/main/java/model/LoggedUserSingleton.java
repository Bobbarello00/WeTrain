package model;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;

import java.sql.SQLException;

public class LoggedUserSingleton {

    private static String fiscalCode;

    private LoggedUserSingleton() {}

    //TODO Creare un controller che comunica con la DAO?
    public static User getInstance() throws SQLException {
        User user = new AthleteDAO().loadAthlete(fiscalCode);
        if(user == null){
            user = new TrainerDAO().loadTrainer(fiscalCode);
        }
        if(user == null){
            System.out.println("Error - Logged User is null.");
        }
        return user;
    }

    public static void setInstance(User user){
        fiscalCode = user.getFiscalCode();
    }
}
