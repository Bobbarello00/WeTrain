package viewone.graphical_controllers.trainers;

import controller.StartLessonController;
import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.bean.CourseBean;
import viewone.bean.StartLessonBean;
import viewone.engeneering.AlertFactory;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class StartLessonGUIController extends AbstractFormGUIController {
    @FXML private TextField urlTextField;

    private final StartLessonController startLessonController = new StartLessonController();
    private CourseBean courseBean;

    @Override protected void sendAction() {
        if(Objects.equals(urlTextField.getText(), "")){
            AlertFactory.newWarningAlert("WAIT...NOT SO FAST!",
                    "Empty Url",
                    "Be sure to paste the url in the text field and try again.");
        }else {
            try {
                startLessonController.startLesson(new StartLessonBean(
                        courseBean,
                        urlTextField.getText()));
            } catch (DBConnectionFailedException e) {
                e.alert();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                AlertFactory.newWarningAlert("OOPS...SOMETHING WENT WRONG!",
                        "Invalid url",
                        "The inserted Url is malformed or not reachable");
            }
        }
    }

    public void setCourse(CourseBean courseBean) {
        this.courseBean = courseBean;
    }
}