package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import model.Course;
import model.notification.Notification;
import model.User;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public void saveNotification(Notification notification) throws SQLException, DBUnreachableException {
        saveNotification(
                notification.getType().ordinal(),
                notification.getDescription(),
                notification.getNotificationDate(),
                notification.getSender().getFiscalCode(),
                notification.getReceiver().getFiscalCode()
        );
    }

    public void saveNotification(int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertNotification(type, info, dateTime, sender, receiver)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Notification> loadAllNotifications(User user) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllNotifications(user)){
            ResultSet rs = preparedStatement.executeQuery();
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
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void deleteNotification(int idNotification) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.deleteNotification(idNotification)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void sendCourseNotification(Course course, Notification notification) throws SQLException, DBUnreachableException {
        try (PreparedStatement preparedStatement = Queries.loadSubscribed(course.getId()); ResultSet rs = preparedStatement.executeQuery()) {
            while(rs.next()) {
                new NotificationDAO().saveNotification(
                        notification.getType().ordinal(),
                        notification.getDescription(),
                        notification.getNotificationDate(),
                        notification.getSender().getFiscalCode(),
                        rs.getString("Athlete")
                );
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
