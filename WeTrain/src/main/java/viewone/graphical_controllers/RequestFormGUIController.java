package viewone.graphical_controllers;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.TextArea;
        import javafx.stage.Stage;
        import viewone.MainPane;

public class RequestFormGUIController extends AbstractFormGUIController{

    @FXML private TextArea requestInfoTextArea;

    @Override void sendButtonAction() {
        System.out.println("Request Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELLA RICHIESTA
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }


}

