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

public class TrainerRegistrationController {
    private static int once = 0;
    @FXML
    private Button attachButt;
    @FXML
    private Text homeButt;
    @FXML
    private Text profileButt;
    @FXML
    private Button infoButt;
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
    protected void attachButtonEntered(){
        attachButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 10");
    }
    @FXML
    protected void attachButtonExited() {
        attachButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 10");
    }
    @FXML
    void attachButtonAction() {
        System.out.println("attach effettuato!");
    }
    @FXML
    void infoButtonAction() {
        System.out.println("info");
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
        PageSwitchSizeChange.pageSwitch(submitButt, "TrainersHome");
    }
    @FXML
    protected void submitButtonEntered(){
        submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void submitButtonExited() {
        submitButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 50");
    }
}
