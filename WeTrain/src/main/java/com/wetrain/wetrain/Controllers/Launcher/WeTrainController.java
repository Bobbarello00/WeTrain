package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class WeTrainController {
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button regButton;
    @FXML
    private Text logInText;
    @FXML
    protected void registerButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ProfileSelection", "Launcher");
        mainPane.setCenter(view);
    }
    @FXML
    protected void logInTextAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("Login", "Launcher");
        mainPane.setCenter(view);
    }
    @FXML
    protected void logInButtonEntered(){
        logInText.setStyle("-fx-fill: rgb(20, 130, 17)");
    }
    @FXML
    protected void logInButtonExited(){
        logInText.setStyle("-fx-fill: rgba(24, 147, 21, 1);");
    }
}
