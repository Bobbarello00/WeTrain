package viewone.graphical_controllers.trainers;

import viewone.MainPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddExerciseController {
    @FXML
    private Button addButton;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea equipmentTextArea;
    @FXML
    private TextField nameText;
    @FXML
    void addButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
    @FXML
    protected void closeAction(ActionEvent event){
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }
    @FXML
    void cancelButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}