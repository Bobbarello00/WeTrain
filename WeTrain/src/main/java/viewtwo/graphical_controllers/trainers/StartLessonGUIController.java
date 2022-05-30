package viewtwo.graphical_controllers.trainers;

import controller.StartLessonController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import exception.NoScheduledLessonException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.bean.CourseBean;
import viewone.bean.StartLessonBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StartLessonGUIController {

    @FXML private TextField urlTextField;

    private CourseBean courseBean;
    private final StartLessonController startLessonController = new StartLessonController();

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("ManageCourses", "trainers");
    }

    @FXML void sendUrlButtonAction() throws IOException {
        if(!(urlTextField.getText().startsWith("https://meet.google.com/"))){
            AlertGenerator.newWarningAlert("URL NOT FROM GOOGLE MEET!",
                    "Invalid Url",
                    "The inserted url seems not to be from google meet, try again.");
        } else {
            try {
                startLessonController.startLesson(new StartLessonBean(
                        courseBean,
                        urlTextField.getText()));
                PageSwitchSimple.switchPage("TrainersHome", "trainers");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
                PageSwitchSimple.logOff();
            } catch (NoScheduledLessonException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
            }
        }
    }

}
