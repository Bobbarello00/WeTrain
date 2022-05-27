package viewtwo.graphical_controllers.launcher;

import javafx.fxml.FXML;
import viewone.MainPane;
import viewtwo.graphical_controllers.PageSwitchSimple;

import java.io.IOException;

public class WeTrainGUIController1 {
    @FXML
    protected void registerAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(), "ProfileSelection1", "launcher1");
    }
    @FXML
    protected void loginAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(), "Login1", "launcher1");
    }
}
