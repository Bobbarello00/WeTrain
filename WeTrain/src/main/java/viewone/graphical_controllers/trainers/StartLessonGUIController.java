package viewone.graphical_controllers.trainers;

import controller.StartLessonController;
import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.bean.CourseBean;
import viewone.bean.StartLessonBean;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;

public class StartLessonGUIController extends AbstractFormGUIController {
    @FXML private TextField urlTextField;

    private final StartLessonController startLessonController = new StartLessonController();
    private CourseBean courseBean;

    @Override protected void sendAction() {
        try {
            startLessonController.startLesson(new StartLessonBean(
                    courseBean,
                    urlTextField.getText()));
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