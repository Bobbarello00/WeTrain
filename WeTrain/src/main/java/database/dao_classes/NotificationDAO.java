package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import model.Course;
import model.Trainer;
import model.notification.CommunicationNotification;
import model.notification.Notification;
import model.User;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public NotificationDAO() throws DBConnectionFailedException {}

    public void saveNotification(Notification notification) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertNotification(stmt, notification);
        }
    }

    public List<Notification> loadAllNotifications(User user) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllNotifications(stmt, user)){
            List<Notification> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(NotificationFactorySingleton.getInstance().createNotification(
                        rs.getInt("idNotification"),
                        rs.getInt("Type"),
                        rs.getString("Info"),
                        rs.getTimestamp("NotificationDate").toLocalDateTime(),
                        new UserDAO().loadUser(rs.getString("sender")),
                        user)
                );
            }

            return myList;
        }
    }

    public void deleteNotification(int idNotification) throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            Query.deleteNotification(stmt, idNotification);
        }
    }

    public void sendCourseNotification(Course course, Trainer trainer, String text) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadSubscribed(stmt, course)) {
            while(rs.next()){
                Notification notification = NotificationFactorySingleton.getInstance().createCommunicationNotification(
                        trainer,
                        new UserDAO().loadUser(rs.getString("Athlete")), //TODO potrebbe essere evitata?
                        course,
                        text);
                new NotificationDAO().saveNotification(notification);
            }
        }
    }
}
