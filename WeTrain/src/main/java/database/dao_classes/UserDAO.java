package database.dao_classes;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import model.Athlete;
import model.Trainer;
import model.User;
import org.jetbrains.annotations.Nullable;
import viewone.engeneering.FatalCaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDAO() {}

    public User loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadUser(email, password)){
            return getUser(preparedStatement.executeQuery());
        }
    }

    public User loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadUser(fc)){
            return getUser(preparedStatement.executeQuery());
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
