package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WeTrainController {
    @FXML
    private Button regButton;
    @FXML
    private Text logInText;
    @FXML
    protected void registerButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", "Launcher");
    }
    @FXML
    protected void closeAction(){
        Stage stage = (Stage) regButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void logInTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"Login", "Launcher");
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
