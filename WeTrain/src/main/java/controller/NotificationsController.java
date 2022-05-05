package controller;

import database.dao_classes.NotificationDAO;
import exception.DBConnectionFailedException;
import model.notification.Notification;
import viewone.bean.NotificationBean;

import java.sql.SQLException;
import java.util.List;

public class NotificationsController {

    private final LoginController loginController = new LoginController();

    public List<NotificationBean> getMyNotification() throws SQLException, DBConnectionFailedException {
        List<Notification> notificationList = new NotificationDAO().loadAllNotifications(loginController.getLoggedUser());
        
    }
}
