package controller;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import viewone.bean.StartLessonBean;

import java.sql.SQLException;

public class StartLessonController {

    public void startLesson(StartLessonBean startLessonBean) throws DBConnectionFailedException, SQLException {
        new CourseDAO().setStartedLessonUrl(startLessonBean.getUrl(), startLessonBean.getCourseBean().getId());
    }
}
