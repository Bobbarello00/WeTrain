package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class AthleteRegistrationController {
    private static int once = 0;
    @FXML
    private Text homeButt;
    @FXML
    private Text profileButt;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button submitButt;
    @FXML
    private Button eyeButt;
    @FXML
    private TextField passwSField;
    @FXML
    private PasswordField passwField;
    @FXML
    private CheckBox checkVisible;
    @FXML
    void passwStart() {
        if (once == 0) {
            PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
            once = 1;
        }
    }
    @FXML
    void eyeButtonAction() {
        checkVisible.fire();
    }
    @FXML
    protected void profileButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ProfileSelection");
        once = 0;
        mainPane.setCenter(view);
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI");
        once = 0;
        mainPane.setCenter(view);
    }
    @FXML
    protected void submitButtonAction() throws IOException {
        once = 0;
        PageSwitchSizeChange.pageSwitch(submitButt, "AthletesHome");
    }
    @FXML
    protected void submitButtonEntered(){
        submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void submitButtonExited() {
        submitButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
}
