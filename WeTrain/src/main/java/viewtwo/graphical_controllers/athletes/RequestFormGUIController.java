package viewtwo.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import exception.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.bean.AthleteBean;
import viewone.bean.RequestBean;
import viewone.bean.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class RequestFormGUIController {

    @FXML private TextArea contentTextArea;

    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

    @FXML void sendButtonAction() {
        try {
            List<UserBean> userBeanList = LoggedUserSingleton.getAthleteAndTrainer();
            RequestBean requestBean = new RequestBean(
                    contentTextArea.getText(),
                    (AthleteBean) userBeanList.get(0),
                    userBeanList.get(1).getFiscalCode());
            requestWorkoutPlanController.sendRequest(requestBean);
            PageSwitchSimple.switchPage("AthletesHome", "athletes");
        } catch (SQLIntegrityConstraintViolationException e){
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "Request already send.",
                    "Wait for your request to be evaluated by the Trainer");
        } catch (SQLException | IOException e){
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }
}
