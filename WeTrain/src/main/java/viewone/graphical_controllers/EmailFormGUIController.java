package viewone.graphical_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.MainPane;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;

    @Override protected void sendAction() {
        System.out.println("Email Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELL'EMAIL
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}
