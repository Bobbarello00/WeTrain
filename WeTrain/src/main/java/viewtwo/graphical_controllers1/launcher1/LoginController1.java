package viewtwo.graphical_controllers1.launcher1;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewone.MainPane;
import viewtwo.graphical_controllers1.PageSwitchSimple1;

import java.io.IOException;

public class LoginController1 {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailTextField;
    @FXML
    protected void backAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");;
    }
    @FXML
    protected void loginAction() throws IOException {
        PageSwitchSimple1.switchPage(MainPane.getInstance(),"WeTrainGUI1","launcher1");;
    }
}
