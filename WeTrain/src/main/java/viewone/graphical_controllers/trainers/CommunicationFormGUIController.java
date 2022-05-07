package viewone.graphical_controllers.trainers;

import controller.NotificationsController;
import exception.DBConnectionFailedException;
import exception.invalidDataException.EmptyFieldsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.bean.CommunicationBean;
import viewone.bean.CourseBean;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;

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
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        } catch (EmptyFieldsException e) {
            e.alert();
            return;
        }
        close();
    }
}
