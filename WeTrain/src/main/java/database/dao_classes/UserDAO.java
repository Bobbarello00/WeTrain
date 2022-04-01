package database.dao_classes;

import database.DatabaseConnection;
import database.Query;
import model.Athlete;
import model.Trainer;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    Connection conn = DatabaseConnection.getInstance().getConn();

    public User loadUser(String fc) {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, fc)) {
            if (rs.next()) {
                String usr = rs.getString("FC");
                AthleteDAO aDao = new AthleteDAO();
                Athlete ret = aDao.loadAthlete(usr);
                if (ret != null) {
                    return ret;
                } else {
                    TrainerDAO tDao = new TrainerDAO();
                    Trainer ret1 = tDao.loadTrainer(usr);
                    if (ret1 != null) {
                        return ret1;
                    }
                    // TODO MALE MALE (non dovevamo arrivare qui -> eccezione) :')
                }
            } else {
                // TODO throw MyException; (user non trovato)
            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }
}
