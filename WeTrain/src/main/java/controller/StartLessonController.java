package controller;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import viewone.bean.CommunicationBean;
import viewone.bean.StartLessonBean;

import java.sql.SQLException;

public class StartLessonController {

    private final NotificationsController notificationsController = new NotificationsController();

    public void startLesson(StartLessonBean startLessonBean) throws DBConnectionFailedException, SQLException {
        new CourseDAO().setStartedLessonUrl(startLessonBean.getUrl(), startLessonBean.getCourseBean().getId());
        notificationsController.sendCourseCommunicationNotification(
                new CommunicationBean(String.format("""
                        The lesson of the course %s has just started.
                        Join it by clicking on the Google Meet logo on the course overview tab.
                        """,
                        startLessonBean.getCourseBean().getName()),
                        startLessonBean.getCourseBean())
        );
    }
}
