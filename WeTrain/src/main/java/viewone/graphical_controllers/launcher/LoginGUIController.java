package viewone.graphical_controllers.launcher;


import controller.LoginController;
import javafx.scene.control.TextField;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import viewone.bean.CredentialsBean;

import java.io.IOException;
import java.sql.SQLException;

public class LoginGUIController extends LauncherGUIController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwField;
    @FXML
    void homeAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", "launcher");
    }
    @FXML
    void submitButtonAction() {
        try {
            LoginController.login(new CredentialsBean(emailField.getText(), passwField.getText()));
        } catch (SQLException e) {
            //TODO Exception
            e.printStackTrace();
        }
    }
}