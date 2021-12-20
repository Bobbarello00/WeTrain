package com.wetrain.wetrain.controllers.launcher;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WeTrainController {
    @FXML
    private Button regButton;
    @FXML
    protected void registerButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", "launcher");
    }
    @FXML
    protected void closeAction(){
        ((Stage) regButton.getScene().getWindow()).close();
    }
    @FXML
    protected void logInTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"Login", "launcher");
    }
}
