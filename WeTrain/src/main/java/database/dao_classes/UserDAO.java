package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.ElementNotFoundException;
import model.Athlete;
import model.Trainer;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public User loadUser(String email, String password) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, email, password)) {
            if (rs.next()) {
                String usr = rs.getString("FC");
                AthleteDAO aDao = new AthleteDAO();
                Athlete ret = aDao.loadAthlete(usr);
                if (ret != null) {
                    return ret;
                } else {
                    TrainerDAO tDao = new TrainerDAO();
                    Trainer ret1 = tDao.loadTrainer(usr);
                    return ret1;
                }
            } else {
                throw new ElementNotFoundException();
            }
        }
    }
}
