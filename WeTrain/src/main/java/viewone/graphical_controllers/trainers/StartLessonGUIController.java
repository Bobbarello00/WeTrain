package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.graphical_controllers.AbstractFormGUIController;

public class StartLessonGUIController extends AbstractFormGUIController {
    @FXML private TextField urlTextField;

    @Override protected void sendAction() {
        urlTextField.getText();
        //TODO salvataggio e settaggio url lezione
    }
}