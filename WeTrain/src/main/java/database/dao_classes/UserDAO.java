package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries.Queries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import models.Athlete;
import models.Trainer;
import models.User;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User loadUser(String email, String password) throws SQLException, DBUnreachableException, UserNotFoundException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_USER_1_QUERY); ResultSet rs = Queries.loadUser(preparedStatement, email, password)){
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
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_USER_2_QUERY); ResultSet rs = Queries.loadUser(fc, preparedStatement)){
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
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.DELETE_USER_QUERY)) {
            Queries.deleteUser(preparedStatement, user.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
