package viewtwo.graphical_controllers.trainers;

import controllers.ProfileManagementController;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.InvalidIbanException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import beans.IbanBean;
import beans.TrainerBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetPaidPageGUIController implements Initializable {

    @FXML private Button editIbanButton;
    @FXML private Pane editPane;
    @FXML private Label ibanLabel;
    @FXML private TextField newIbanTextField;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", "trainers");
    }

    @FXML void editCancelButtonAction() {
        setEditPaneVisible(false);
    }

    @FXML void editConfirmButtonAction() {
        try {
            IbanBean ibanBean = IbanBean.ctorWithSyntaxCheck(newIbanTextField.getText());
            profileManagementController.updateTrainerIban(ibanBean);
            TrainerBean trainer = (TrainerBean) LoggedUserSingleton.getInstance();
            setIbanLabel(trainer);
            setEditPaneVisible(false);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidIbanException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }

    @FXML void editIbanButtonAction() {
        setEditPaneVisible(true);
    }

    private void setEditPaneVisible(boolean bool) {
        editPane.setVisible(bool);
        editPane.setDisable(!bool);
        editIbanButton.setVisible(!bool);
        editIbanButton.setDisable(bool);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TrainerBean trainerBean = (TrainerBean) LoggedUserSingleton.getInstance();
            setIbanLabel(trainerBean);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }

    private void setIbanLabel(TrainerBean trainerBean) {
        if(trainerBean.getIban() == null){
            ibanLabel.setText("Not inserted yet");
        } else {
            ibanLabel.setText(trainerBean.getIban());
        }
    }
}
