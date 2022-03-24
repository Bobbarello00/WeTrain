package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.Notification;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    Connection conn = DatabaseConnection.getInstance().conn;

    public List<Notification> loadAllNotifications(User user) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllNotifications(stmt, user)){
            List<Notification> myList = new ArrayList<Notification>();
            while(rs.next()){
                myList.add(new Notification(rs.getInt("idNotification"),rs.getInt("Type"),rs.getString("Info"),
                        rs.getTimestamp("NotificationDate").toLocalDateTime(),user));
            }
            return myList;
        }

    }
}
