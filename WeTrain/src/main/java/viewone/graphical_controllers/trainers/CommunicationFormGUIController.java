package viewone.graphical_controllers.trainers;

import controllers.NotificationsController;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.EmptyFieldsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.PageSwitchSizeChange;
import beans.CommunicationBean;
import beans.CourseBean;
import engeneering.AlertGenerator;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;
import java.util.List;

public class CommunicationFormGUIController extends AbstractFormGUIController {
    @FXML private TextArea contentTextArea;

    private CourseBean courseBean;
    private final NotificationsController notificationsController = new NotificationsController();

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    @Override protected void sendAction() {
        try {
            notificationsController.sendCourseCommunicationNotification(new CommunicationBean(
                    contentTextArea.getText(),
                    courseBean
            ));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (EmptyFieldsException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            return;
        }
        close();
    }
}
