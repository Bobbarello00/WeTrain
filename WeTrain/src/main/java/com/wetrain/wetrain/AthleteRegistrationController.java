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
        PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
    }
    @FXML
    void eyeButtonAction() {checkVisible.fire();}
    @FXML
    protected void profileButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("ProfileSelection");
        mainPane.setCenter(view);
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    protected void submitButtonAction() throws IOException {PageSwitchAfterLog.pageSwitch(submitButt, "AthletesHome");}
    @FXML
    protected void submitButtonEntered(){
        submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void submitButtonExited() {
        submitButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
}
