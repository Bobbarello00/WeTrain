package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.LessonDAO;
import exception.BrowsingNotSupportedException;
import exception.DBUnreachableException;
import exception.NoScheduledLessonException;
import exception.UrlNotInsertedYetException;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewone.engeneering.AlertFactory;

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
                lessonUrl =  new LessonDAO().loadStartedLessonUrl(idLesson);
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertFactory.newWarningAlert(
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
            new CourseDAO().deleteStartedLessonUrl(courseBean.getId());
            throw new NoScheduledLessonException();
        }
    }

    private boolean isValidTime(LessonBean lessonBean) {
        return LocalTime.now().getHour() >= lessonBean.getLessonStartTime().getHour()
                && LocalTime.now().getMinute() >= lessonBean.getLessonStartTime().getMinute()
                && LocalTime.now().getHour() <= lessonBean.getLessonEndTime().getHour()
                && LocalTime.now().getMinute() <= lessonBean.getLessonEndTime().getMinute();
    }
}
