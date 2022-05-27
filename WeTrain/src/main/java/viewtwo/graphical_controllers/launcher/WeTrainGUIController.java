package viewtwo.graphical_controllers.launcher;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class WeTrainGUIController {

    @FXML protected void registerAction() throws IOException {
        PageSwitchSimple.switchPage("ProfileSelection", "launcher");
    }
    @FXML protected void loginAction() throws IOException {
        PageSwitchSimple.switchPage("Login", "launcher");
    }
}
