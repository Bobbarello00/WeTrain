package viewone.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import exception.DBUnreachableException;
import exception.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import viewone.PageSwitchSizeChange;
import viewone.bean.AthleteBean;
import viewone.bean.RequestBean;
import viewone.bean.UserBean;
import viewone.engeneering.AlertGenerator;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class RequestFormGUIController extends AbstractFormGUIController {

    @FXML private TextArea requestInfoTextArea;
    private UserBean trainer;

    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();

    public void setTrainer(UserBean trainer) {
        this.trainer = trainer;
    }

    @Override protected void sendAction() {
        try {
            RequestBean requestBean = new RequestBean(
                    requestInfoTextArea.getText(),
                    (AthleteBean) LoggedUserSingleton.getInstance(),
                    trainer.getFiscalCode());
            requestWorkoutPlanController.sendRequest(requestBean);
        } catch (SQLIntegrityConstraintViolationException e){
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "Request already send.",
                    "Wait for your request to be evaluated by the Trainer");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            ((Stage) sendButton.getScene().getWindow()).close();
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return;
        } catch (InvalidDataException e) {
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

