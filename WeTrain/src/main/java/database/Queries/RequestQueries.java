package database.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RequestQueries extends Queries{

    public static final String INSERT_REQUEST_QUERY = "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertRequest(PreparedStatement preparedStatement, LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
        preparedStatement.setString(2, info);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.setString(4, trainerFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_REQUESTS_QUERY = "SELECT Request.* " +
            "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadTrainerRequests(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String DELETE_REQUEST_QUERY = "DELETE FROM mydb.Request " +
            "WHERE idRequest = ?";
    public static void deleteRequest(PreparedStatement preparedStatement, int idRequest) throws SQLException {
        preparedStatement.setInt(1, idRequest);
        preparedStatement.executeUpdate();
    }
}