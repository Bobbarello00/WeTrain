package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.NotificationDAO;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Course;
import model.User;
import model.notification.Notification;
import model.notification.NotificationEnum;
import viewone.bean.CommunicationBean;
import viewone.bean.EmailReceivedNotificationBean;
import viewone.bean.NotificationBean;
import viewone.bean.UserBean;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public void sendCourseCommunicationNotification(CommunicationBean bean) throws SQLException, DBConnectionFailedException {
        Course course = new CourseDAO().loadCourse(bean.getCourseBean().getId());
        new NotificationDAO().sendCourseNotification(
                course,
                NotificationFactorySingleton.getInstance().createCourseCommunicationNotification(
                        loginController.getLoggedUser(),
                        course,
                        bean.getText()
                ));
    }

    public void sendRejectRequestNotification(String receiver) throws SQLException, DBConnectionFailedException {
        Notification notification = NotificationFactorySingleton.getInstance().createRejectedRequestNotification(
                loginController.getLoggedUser()
        );
        new NotificationDAO().saveNotification(
                notification.getType().ordinal(),
                notification.getDescription(),
                LocalDateTime.now(),
                notification.getSender().getFiscalCode(),
                receiver
        );
    }

    public void deleteNotification(NotificationBean notificationBean) throws DBConnectionFailedException, SQLException {
        new NotificationDAO().deleteNotification(notificationBean.getId());
    }

    public void sendEmailReceivedNotification(EmailReceivedNotificationBean emailReceivedNotificationBean) throws DBConnectionFailedException, SQLException {
        new NotificationDAO().saveNotification(
                NotificationEnum.EMAILRECEIVED.ordinal(),
                "",
                LocalDateTime.now(),
                emailReceivedNotificationBean.getSender().getFiscalCode(),
                emailReceivedNotificationBean.getReceiver().getFiscalCode()
        );
    }

    public void sendSubscriptionToACourseNotification(User sender, User receiver, Course course) throws DBConnectionFailedException, SQLException {
        Notification notification = NotificationFactorySingleton.getInstance().createSubscribeToCourseNotification(
                sender,
                receiver,
                course,
                new CourseDAO().getSubscribersNumber(course.getId())
        );
        new NotificationDAO().saveNotification(notification);
    }
}
