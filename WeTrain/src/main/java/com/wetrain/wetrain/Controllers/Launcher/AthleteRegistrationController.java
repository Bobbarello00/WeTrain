package com.wetrain.wetrain.Controllers.Launcher;


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

public class AthleteRegistrationController implements Initializable {
    @FXML
    private Text homeText;
    @FXML
    private Text profileText;
    @FXML
    private ImageView logo;
    @FXML
    private Button continueButton;
    @FXML
    private Button eyeButton;
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
    protected void profileButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", "Launcher");
    }
    @FXML
    protected void closeAction(){
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", "Launcher");
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        MoreInfoController.string = "Athlete";
        PageSwitchSimple.switchPage(MainPane.getInstance(),"MoreInfo", "Launcher");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
    }
}
