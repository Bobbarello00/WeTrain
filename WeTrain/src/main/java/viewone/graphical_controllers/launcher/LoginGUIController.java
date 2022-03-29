package viewone.graphical_controllers.launcher;


import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginGUIController extends LauncherGUIController {
    @FXML
    void homeAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", "launcher");
    }
    @FXML
    void submitButtonAction() {
        System.out.println("Login effettuato!");
    }
}