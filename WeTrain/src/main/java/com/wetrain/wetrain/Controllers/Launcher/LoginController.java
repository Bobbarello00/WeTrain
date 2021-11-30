package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PasswordBehaviorActivation;
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
    private static int once = 0;
    @FXML
    private Text homeText;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button submitButton;
    @FXML
    private Button eyeButton;
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
    void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI", "Launcher");
        once = 0;
        mainPane.setCenter(view);
    }
    @FXML
    void submitButtonAction() {
        System.out.println("Login effettuato!");
    }
}