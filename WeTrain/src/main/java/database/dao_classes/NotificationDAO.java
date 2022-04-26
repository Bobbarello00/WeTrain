package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import model.Notification;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public NotificationDAO() throws DBConnectionFailedException {
    }

    public void saveNotification(Notification notification) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertNotification(stmt,notification);
        }
    }

    public List<Notification> loadAllNotifications(User user) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllNotifications(stmt, user)){
            List<Notification> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(new Notification(
                        rs.getInt("idNotification"),
                        rs.getInt("Type"),
                        rs.getString("Info"),
                        rs.getTimestamp("NotificationDate").toLocalDateTime(),
                        user)
                );
            }
            return myList;
        }
    }
}
