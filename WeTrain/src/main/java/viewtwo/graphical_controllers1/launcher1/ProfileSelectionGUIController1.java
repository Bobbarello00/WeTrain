package viewtwo.graphical_controllers1.launcher1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import viewone.MainPane;
import viewtwo.graphical_controllers1.PageSwitchSimple1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSelectionGUIController1 implements Initializable {
    private final RadioButton notSelected = new RadioButton();
    @FXML
    private RadioButton trainerSelection;
    @FXML
    private RadioButton athleteSelection;
    @FXML
    protected void backAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
    @FXML
    protected void nextAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup=new ToggleGroup();
        notSelected.setToggleGroup(toggleGroup);
        trainerSelection.setToggleGroup(toggleGroup);
        athleteSelection.setToggleGroup(toggleGroup);
        notSelected.fire();
    }
}