package viewone.graphical_controllers.trainers;

import controller.CourseManagementTrainerController;
import exception.DBConnectionFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.bean.CommunicationBean;
import viewone.bean.CourseBean;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;

public class CommunicationFormGUIController extends AbstractFormGUIController {
    @FXML private TextArea contentTextArea;

    private CourseBean courseBean;
    private final CourseManagementTrainerController courseManagementTrainerController = new CourseManagementTrainerController();

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    @Override protected void sendAction() {
        try {
            courseManagementTrainerController.sendCourseCommunication(new CommunicationBean(
                    contentTextArea.getText(),
                    courseBean
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
        close();
    }
}
