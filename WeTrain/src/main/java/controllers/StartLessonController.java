package controllers;

import database.dao_classes.LessonDAO;
import exceptions.DBUnreachableException;
import exceptions.NoScheduledLessonException;
import exceptions.invalid_data_exception.EmptyFieldsException;
import exceptions.runtime_exception.FatalErrorException;
import beans.CommunicationBean;
import beans.LessonBean;
import beans.StartLessonBean;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class StartLessonController {

    private final NotificationsController notificationsController = new NotificationsController();

    public void startLesson(StartLessonBean startLessonBean) throws DBUnreachableException, SQLException, NoScheduledLessonException {
        int idLesson = 0;
        for(LessonBean lessonBean: startLessonBean.getCourseBean().getLessonBeanList()) {
            if((DayOfWeek.from(LocalDate.now()).name().equals(lessonBean.getLessonDay())) && (isValidTime(lessonBean))) {
                idLesson = lessonBean.getId();
            }
        }
        if(idLesson != 0) {
            new LessonDAO().setStartedLessonUrl(startLessonBean.getUrl(), idLesson);
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
                throw new FatalErrorException();
            }
        } else {
            throw new NoScheduledLessonException("NO SCHEDULE LESSON NOW");
        }
    }

    private boolean isValidTime(LessonBean lessonBean) {
        boolean cond1 = LocalTime.now().getHour() > lessonBean.getLessonStartTime().getHour()
                ||(LocalTime.now().getHour() == lessonBean.getLessonStartTime().getHour()
                && LocalTime.now().getMinute() >= lessonBean.getLessonStartTime().getMinute());
        boolean cond2 = (LocalTime.now().getHour() < lessonBean.getLessonEndTime().getHour()
                || (LocalTime.now().getHour() == lessonBean.getLessonEndTime().getHour()
                && LocalTime.now().getMinute() <= lessonBean.getLessonEndTime().getMinute()));
        return cond1 && cond2;
    }
}
