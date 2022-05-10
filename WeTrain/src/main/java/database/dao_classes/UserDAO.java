package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import model.Athlete;
import model.Trainer;
import model.User;
import org.jetbrains.annotations.Nullable;
import viewone.engeneering.FatalCaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDAO() {}

    public User loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        try (ResultSet rs = Queries.loadUser(email, password)) {
            return getUser(rs);
        }
    }

    public User loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try (ResultSet rs = Queries.loadUser(fc)) {
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
