package database.Queries;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NotificationQueries extends Queries{

    private NotificationQueries() {}

    public static final String LOAD_ALL_NOTIFICATIONS_QUERY = SELECT_ALL +
            "FROM mydb.Notification " +
            "WHERE Receiver = ? " +
            LIMIT_30;
    public static ResultSet loadAllNotifications(PreparedStatement preparedStatement, User receiver) throws SQLException {
        preparedStatement.setString(1, receiver.getFiscalCode());
        preparedStatement.closeOnCompletion();
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_NOTIFICATION_QUERY = "INSERT INTO mydb.Notification (Type, Info, NotificationDate, Sender, Receiver) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static void insertNotification(PreparedStatement preparedStatement, int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException {
        preparedStatement.setInt(1, type);
        preparedStatement.setString(2, info);
        preparedStatement.setTimestamp(3, Timestamp.valueOf(dateTime));
        preparedStatement.setString(4, sender);
        preparedStatement.setString(5, receiver);
        preparedStatement.executeUpdate();
    }

    public static final String DELETE_NOTIFICATION_QUERY = "DELETE FROM mydb.Notification " +
            "WHERE idNotification = ?";
    public static void deleteNotification(PreparedStatement preparedStatement, int idNotification) throws SQLException {
        preparedStatement.setInt(1, idNotification);
        preparedStatement.executeUpdate();
    }
}
