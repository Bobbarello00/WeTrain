package viewtwo.graphical_controllers1.launcher1;

import javafx.fxml.FXML;
import viewone.MainPane;
import viewtwo.graphical_controllers1.PageSwitchSimple1;

import java.io.IOException;

public class WeTrainGUIController1 {
    @FXML
    protected void registerAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(), "ProfileSelection1", "launcher1");
    }
    @FXML
    protected void loginAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(), "Login1", "launcher1");
    }
}
