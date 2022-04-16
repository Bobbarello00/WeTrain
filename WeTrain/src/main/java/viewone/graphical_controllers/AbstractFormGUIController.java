package viewone.graphical_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import viewone.MainPane;

public abstract class AbstractFormGUIController{
    @FXML protected Button sendButton;

    @FXML protected void closeAction(){
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML abstract void sendButtonAction();
}
