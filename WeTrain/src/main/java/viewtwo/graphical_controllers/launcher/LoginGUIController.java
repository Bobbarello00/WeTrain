package viewtwo.graphical_controllers.launcher;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewone.MainPane;
import viewtwo.graphical_controllers.PageSwitchSimple;

import java.io.IOException;

public class LoginGUIController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailTextField;
    @FXML
    protected void backAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
    @FXML
    protected void loginAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");
    }
}
