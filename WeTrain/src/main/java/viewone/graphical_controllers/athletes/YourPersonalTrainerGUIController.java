package viewone.graphical_controllers.athletes;

import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class YourPersonalTrainerGUIController extends HomeGUIControllerAthletes {
    @FXML
    private Label trainerName;
    @FXML
    private VBox trainerBox;
    @FXML
    private VBox addTrainerBox;
    //TODO Implementare metodi
    @FXML
    void workoutRequestButtonAction() {

    }
    @FXML
    void unsubscribeButtonAction() {
        trainerBox.setDisable(true);
        trainerBox.setVisible(false);
        addTrainerBox.setDisable(false);
        addTrainerBox.setVisible(true);
    }
    @FXML
    void addTrainerAction() {
        addTrainerBox.setDisable(true);
        addTrainerBox.setVisible(false);
        trainerBox.setDisable(false);
        trainerBox.setVisible(true);
    }
    @FXML
    void writeEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "EmailForm", "", false);
    }
}
