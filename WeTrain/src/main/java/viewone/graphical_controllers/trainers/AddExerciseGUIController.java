package viewone.graphical_controllers.trainers;

import viewone.MainPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddExerciseGUIController {
    @FXML private Button addButton;
    @FXML private TextArea descriptionTextArea;
    @FXML private TextArea equipmentTextArea;
    @FXML private TextField nameText;

    @FXML void addButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void cancelButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}