package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import exception.UserNotFoundException;
import exception.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import model.Athlete;
import model.Trainer;
import model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User loadUser(String email, String password) throws SQLException, DBUnreachableException, UserNotFoundException {
        try(PreparedStatement preparedStatement = Queries.loadUser(email, password)){
            return getUser(preparedStatement.executeQuery());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public User loadUser(String fc) throws SQLException, DBUnreachableException, UserNotFoundException {
        try(PreparedStatement preparedStatement = Queries.loadUser(fc)){
            return getUser(preparedStatement.executeQuery());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    private @NotNull User getUser(ResultSet rs) throws SQLException, DBUnreachableException, UserNotFoundException {
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
                throw new IsNeitherATrainerNorAnAthleteException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteUser(User user) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.deleteUser(user.getFiscalCode())){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
