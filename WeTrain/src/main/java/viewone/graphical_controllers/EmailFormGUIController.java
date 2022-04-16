package viewone.graphical_controllers;

import javafx.scene.control.TextField;
import viewone.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EmailFormGUIController {
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;
    @FXML private Button sendButton;

    @FXML protected void closeAction(){
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void sendButtonAction() {
        System.out.println("Email Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELL'EMAIL
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}
