package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Course;
import model.User;
import model.notification.Notification;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public NotificationDAO() {}

    public void saveNotification(Notification notification) throws SQLException, DBConnectionFailedException {
        saveNotification(
                notification.getType().ordinal(),
                notification.getDescription(),
                notification.getNotificationDate(),
                notification.getSender().getFiscalCode(),
                notification.getReceiver().getFiscalCode()
        );
    }

    public void saveNotification(int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBConnectionFailedException {
        Queries.insertNotification(type, info, dateTime, sender, receiver);
    }

    public List<Notification> loadAllNotifications(User user) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadAllNotifications(user)){
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

    public void deleteNotification(int idNotification) throws SQLException, DBConnectionFailedException {
        Queries.deleteNotification(idNotification);
    }

    public void sendCourseNotification(Course course, Notification notification) throws SQLException, DBConnectionFailedException {
        try (ResultSet rs = Queries.loadSubscribed(course.getId())) {
            while(rs.next()) {
                new NotificationDAO().saveNotification(
                        notification.getType().ordinal(),
                        notification.getDescription(),
                        notification.getNotificationDate(),
                        notification.getSender().getFiscalCode(),
                        rs.getString("Athlete")
                );
            }
        }
    }
}
