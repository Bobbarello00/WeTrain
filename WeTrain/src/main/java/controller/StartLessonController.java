package controller;

import database.dao_classes.CourseDAO;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import viewone.bean.CommunicationBean;
import viewone.bean.StartLessonBean;

import java.sql.SQLException;

public class StartLessonController {

    private final NotificationsController notificationsController = new NotificationsController();

    public void startLesson(StartLessonBean startLessonBean) throws DBUnreachableException, SQLException {
        new CourseDAO().setStartedLessonUrl(startLessonBean.getUrl(), startLessonBean.getCourseBean().getId());
        try {
            notificationsController.sendCourseCommunicationNotification(
                    new CommunicationBean(String.format("""
                            The lesson of the course %s has just started.
                            Join it by clicking on the Google Meet logo on the course overview tab.
                            """,
                            startLessonBean.getCourseBean().getName()),
                            startLessonBean.getCourseBean())
            );
        } catch (EmptyFieldsException e) {
            throw new RuntimeException(e);
        }
    }
}
