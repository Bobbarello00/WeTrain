package viewtwo.graphical_controllers1.launcher1;

import javafx.fxml.FXML;
import viewone.MainPane;
import viewtwo.graphical_controllers1.PageSwitchSimple1;

import java.io.IOException;

public class ProfileSelectionController1 {
    @FXML
    protected void backAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
    @FXML
    protected void nextAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
}
