package com.wetrain.wetrain.controllers.launcher;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PasswordBehaviorActivation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    private static final String HOME = "launcher";
    @FXML
    private Button continueButton;
    @FXML
    private TextField passwSField;
    @FXML
    private PasswordField passwField;
    @FXML
    private CheckBox checkVisible;
    @FXML
    void eyeButtonAction() {
        checkVisible.fire();
    }
    @FXML
    protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"MoreInfo", HOME);
    }
    @FXML
    protected void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
    }
    @FXML
    protected void closeAction(){
        ((Stage) continueButton.getScene().getWindow()).close();
    }
}
