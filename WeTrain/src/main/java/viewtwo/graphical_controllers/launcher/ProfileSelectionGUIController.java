package viewtwo.graphical_controllers.launcher;

import engeneering.AlertGenerator;
import exception.runtime_exception.FatalErrorException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSelectionGUIController implements Initializable {
    private final RadioButton notSelected = new RadioButton();
    @FXML private RadioButton trainerSelection;
    @FXML private RadioButton athleteSelection;

    @FXML protected void backAction() throws IOException {
        PageSwitchSimple.switchPage("WeTrainGUI","launcher");
    }

    @FXML protected void nextAction() throws IOException {
        String selectedProfile;
        if(trainerSelection.isSelected()) {
            selectedProfile = "Trainer";
        } else if (athleteSelection.isSelected()) {
            selectedProfile = "Athlete";
        } else {
            AlertGenerator.newWarningAlert("OOPS... SOMETHING WENT WRONG!",
                    "Gender not selected.",
                    "Select your gender with the checkbox at the bottom of the page");
            return;
        }
        RegistrationGUIController controller = (RegistrationGUIController) PageSwitchSimple.switchPage("Registration","launcher");
        if(controller == null) {
            throw new FatalErrorException();
        }
        controller.setSelectedProfile(selectedProfile);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        notSelected.setToggleGroup(toggleGroup);
        trainerSelection.setToggleGroup(toggleGroup);
        athleteSelection.setToggleGroup(toggleGroup);
        notSelected.fire();
    }
}