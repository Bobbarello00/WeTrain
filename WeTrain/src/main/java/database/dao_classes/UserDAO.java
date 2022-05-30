package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import exception.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import model.Athlete;
import model.Trainer;
import model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User loadUser(String email, String password) throws SQLException, DBUnreachableException, UserNotFoundException {
        try(ResultSet rs = Queries.loadUser(email, password)){
            if(rs.next()) {
                return getUser(rs.getString("FC"));
            } else {
                throw new UserNotFoundException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public User loadUser(String fc) throws SQLException, DBUnreachableException, UserNotFoundException {
        try(ResultSet rs = Queries.loadUser(fc)){
            if(rs.next()) {
                return getUser(rs.getString("FC"));
            } else {
                throw new UserNotFoundException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    private @NotNull User getUser(String fc) throws SQLException, DBUnreachableException {
        AthleteDAO aDao = new AthleteDAO();
        Athlete ret = aDao.loadAthlete(fc);
        if (ret != null) {
            return ret;
        } else {
            TrainerDAO tDao = new TrainerDAO();
            Trainer ret1 = tDao.loadTrainer(fc);
            if(ret1 != null) {
                return ret1;
            }
            throw new IsNeitherATrainerNorAnAthleteException();
        }
    }

    public void deleteUser(User user) throws SQLException, DBUnreachableException {
        try {
            Queries.deleteUser(user.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
