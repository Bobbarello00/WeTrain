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

public class LoginController {
    @FXML
    private Text homeButt;
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
    void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    void submitButtonAction() {
        System.out.println("Submit effettuato!");
    }
    @FXML
    void submitButtonEntered() {
        submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    void submitButtonExited() {
        submitButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 50");
    }
}