package viewtwo.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.TextOutOfBoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.bean.AthleteBean;
import viewone.bean.RequestBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;

public class RequestFormGUIController {

    @FXML private TextArea contentTextArea;

    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();

    @FXML void backButtonAction(ActionEvent event) throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

    @FXML void sendButtonAction(ActionEvent event) {
        try {
            RequestBean requestBean = new RequestBean(
                    contentTextArea.getText(),
                    (AthleteBean) LoggedUserSingleton.getInstance(),
                    trainer.getFiscalCode());
            requestWorkoutPlanController.sendRequest(requestBean);
        } catch (DBUnreachableException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (TextOutOfBoundException e) {
            throw new RuntimeException(e);
        } catch (EmptyFieldsException e) {
            throw new RuntimeException(e);
        }
    }
}
