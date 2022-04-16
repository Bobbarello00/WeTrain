package viewone.graphical_controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import viewone.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;


    @Override void sendButtonAction() {
        System.out.println("Email Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELL'EMAIL
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}
