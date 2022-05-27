package viewtwo.graphical_controllers.launcher;

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
        PageSwitchSimple.switchPage("Registration","launcher");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        notSelected.setToggleGroup(toggleGroup);
        trainerSelection.setToggleGroup(toggleGroup);
        athleteSelection.setToggleGroup(toggleGroup);
        notSelected.fire();
    }
}