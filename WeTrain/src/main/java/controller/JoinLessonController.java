package controller;

import database.dao_classes.CourseDAO;
import exception.BrowsingNotSupportedException;
import exception.DBConnectionFailedException;
import exception.UrlNotInsertedYetException;
import viewone.bean.IdBean;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class JoinLessonController {

    public void joinLesson(IdBean idBean) throws UrlNotInsertedYetException, URISyntaxException, IOException, BrowsingNotSupportedException {
        String lessonUrl = null;
        try {
            lessonUrl = new CourseDAO().loadStartedLessonUrl(idBean.getId());
        } catch (DBConnectionFailedException e) {
            e.alert();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(lessonUrl == null){
            throw new UrlNotInsertedYetException();
        }
        Desktop desktop = Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.BROWSE)){
            desktop.browse(new URI(lessonUrl));
        }else{
            throw new BrowsingNotSupportedException();
        }
    }
}
