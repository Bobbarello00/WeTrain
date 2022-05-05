package viewone.graphical_controllers.trainers;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.bean.CourseBean;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;

public class StartLessonGUIController extends AbstractFormGUIController {
    @FXML private TextField urlTextField;

    private CourseBean courseBean;

    @Override protected void sendAction() {
        try {
            new CourseDAO().setStartedLessonUrl(urlTextField.getText(), courseBean.getId());
        } catch (DBConnectionFailedException e) {
            e.alert();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCourse(CourseBean courseBean) {
        this.courseBean = courseBean;
    }
}