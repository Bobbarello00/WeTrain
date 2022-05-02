package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import model.Athlete;
import model.Trainer;
import model.User;
import org.jetbrains.annotations.Nullable;
import viewone.engeneering.FatalCaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public UserDAO() throws DBConnectionFailedException {
    }

    public User loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, email, password)) {
            return getUser(rs);
        }
    }

    public User loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, fc)) {
            return getUser(rs);
        }
    }

    @Nullable
    private User getUser(ResultSet rs) throws SQLException, DBConnectionFailedException {
        if (rs.next()) {
            String usr = rs.getString("FC");
            AthleteDAO aDao = new AthleteDAO();
            Athlete ret = aDao.loadAthlete(usr);
            if (ret != null) {
                return ret;
            } else {
                TrainerDAO tDao = new TrainerDAO();
                Trainer ret1 = tDao.loadTrainer(usr);
                if(ret1 != null) {
                    return ret1;
                }
                FatalCaseManager.killApplication();
                return null;
            }
        } else {
            throw new ElementNotFoundException();
        }
    }
}
