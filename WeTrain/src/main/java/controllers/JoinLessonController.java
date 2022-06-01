package controllers;

import database.dao_classes.LessonDAO;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import exceptions.NoScheduledLessonException;
import exceptions.UrlNotInsertedYetException;
import viewone.beans.CourseBean;
import viewone.beans.LessonBean;
import engeneering.AlertGenerator;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class JoinLessonController {

    public void joinLesson(CourseBean courseBean) throws UrlNotInsertedYetException, URISyntaxException, IOException, BrowsingNotSupportedException, NoScheduledLessonException, DBUnreachableException, SQLException {
        int idLesson = 0;
        for(LessonBean lessonBean: courseBean.getLessonBeanList()) {
            if((DayOfWeek.from(LocalDate.now()).name().equals(lessonBean.getLessonDay())) && (isValidTime(lessonBean))) {
                idLesson = lessonBean.getId();
            }
        }
        if(idLesson != 0){
            String lessonUrl = null;
            try {
                lessonUrl = new LessonDAO().loadStartedLessonUrl(idLesson);
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            if (lessonUrl == null) {
                throw new UrlNotInsertedYetException();
            }
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(lessonUrl));
            } else {
                throw new BrowsingNotSupportedException();
            }
        } else {
            new LessonDAO().deleteStartedLessonUrl(courseBean.getId());
            throw new NoScheduledLessonException();
        }
    }

    private boolean isValidTime(LessonBean lessonBean) {
        return LocalTime.now().getHour() > lessonBean.getLessonStartTime().getHour()
                ||(LocalTime.now().getHour() == lessonBean.getLessonStartTime().getHour()
                && LocalTime.now().getMinute() >= lessonBean.getLessonStartTime().getMinute())
                && (LocalTime.now().getHour() < lessonBean.getLessonEndTime().getHour()
                || (LocalTime.now().getHour() == lessonBean.getLessonEndTime().getHour()
                && LocalTime.now().getMinute() <= lessonBean.getLessonEndTime().getMinute()));
    }
}
