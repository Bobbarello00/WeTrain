package viewone.graphical_controllers.athletes;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.graphical_controllers.AbstractFormGUIController;

public class RequestFormGUIController extends AbstractFormGUIController {

    @FXML private TextArea requestInfoTextArea;

    public void

    @Override protected void sendAction() {

        System.out.println("Request Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELLA RICHIESTA
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }


}

