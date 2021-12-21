package com.wetrain.wetrain.graphical_controllers.athletes;

import com.wetrain.wetrain.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class RequestFormController {
    @FXML
    private TextArea motivationTextArea;
    @FXML
    private Button sendButton;
    @FXML
    protected void closeAction(){
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
    @FXML
    void sendButtonAction() {
        System.out.println("Request Sent!");
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}
