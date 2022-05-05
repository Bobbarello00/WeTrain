package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.NotificationDAO;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Course;
import model.Trainer;
import model.notification.Notification;
import viewone.bean.CommunicationBean;
import viewone.bean.NotificationBean;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsController {

    private final LoginController loginController = new LoginController();

    public List<NotificationBean> getMyNotification() throws SQLException, DBConnectionFailedException {
        List<Notification> notificationList = new NotificationDAO().loadAllNotifications(loginController.getLoggedUser());
        List<NotificationBean> notificationBeanList = new ArrayList<>();
        for(Notification notification: notificationList) {
            notificationBeanList.add(getNotificationBean(notification));
        }
        return notificationBeanList;
    }

    private NotificationBean getNotificationBean(Notification notification) {
        String type;
        if(notification.getSender() instanceof Athlete) {
            type = "Athlete";
        } else {
            type = "Trainer";
        }
        UserBean sender = new UserBean(
                notification.getSender().getUsername(),
                notification.getSender().getName(),
                notification.getSender().getSurname(),
                notification.getSender().getFiscalCode(),
                notification.getSender().getDateOfBirth(),
                type,
                notification.getSender().getGender(),
                notification.getSender().getEmail(),
                notification.getSender().getPassword()
        );
        UserBean receiver = new UserBean(
                notification.getReceiver().getUsername(),
                notification.getReceiver().getName(),
                notification.getReceiver().getSurname(),
                notification.getReceiver().getFiscalCode(),
                notification.getReceiver().getDateOfBirth(),
                type,
                notification.getReceiver().getGender(),
                notification.getReceiver().getEmail(),
                notification.getReceiver().getPassword()
        );
        return new NotificationBean(
                notification.getId(),
                sender,
                receiver,
                notification.getNotificationDate(),
                notification.promptMessage()
        );
    }

    public void sendCourseCommunication(CommunicationBean bean) throws SQLException, DBConnectionFailedException {
        Course course = new CourseDAO().loadCourse(bean.getCourseBean().getId());
        new NotificationDAO().sendCourseNotification(
                course,
                (Trainer) loginController.getLoggedUser(),
                bean.getText());
    }

    public void deleteNotification(NotificationBean notificationBean) throws DBConnectionFailedException, SQLException {
        new NotificationDAO().deleteNotification(notificationBean.getId());
    }
}
