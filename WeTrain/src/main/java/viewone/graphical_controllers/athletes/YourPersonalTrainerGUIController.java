package viewone.graphical_controllers.athletes;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourPersonalTrainerGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Label trainerName;
    @FXML private VBox trainerBox;
    @FXML private VBox addTrainerBox;

    @FXML void workoutRequestButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "RequestForm", "", false);
    }

    @FXML void unsubscribeButtonAction() {
        trainerBox.setDisable(true);
        trainerBox.setVisible(false);
        addTrainerBox.setDisable(false);
        addTrainerBox.setVisible(true);
    }

    @FXML void addTrainerAction(ActionEvent event) throws IOException {
        addTrainerBox.setDisable(true);
        addTrainerBox.setVisible(false);
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "FindTrainer", "", false);
        //TODO SET DEI VALORI DEL TRAINER NUOVO OPPURE SE IL TRAINER E' ANCORA NULL -> rimettere tasto + sulla GUI
        trainerBox.setDisable(false);
        trainerBox.setVisible(true);
    }

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "EmailForm", "", false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        setUsername();
    }
}
