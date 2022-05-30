package viewtwo.graphical_controllers.trainers;

import controller.NotificationsController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.PageSwitchSizeChange;
import viewone.bean.CommunicationBean;
import viewone.bean.CourseBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WriteCommunicationGUIController {

    @FXML private TextArea communicationText;

    private CourseBean courseBean;
    private final NotificationsController notificationsController = new NotificationsController();

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("ManageCourses", "trainers");
    }

    @FXML void sendCommunicationButtonAction() {
        try {
            notificationsController.sendCourseCommunicationNotification(new CommunicationBean(
                    communicationText.getText(),
                    courseBean
            ));
        } catch (EmptyFieldsException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
