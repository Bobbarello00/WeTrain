package viewone.graphical_controllers.athletes;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class YourPersonalTrainerGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Label trainerName;
    @FXML private VBox trainerBox;
    @FXML private VBox addTrainerBox;

    //TODO Implementare metodi
    @FXML void workoutRequestButtonAction() {

    }

    @FXML void unsubscribeButtonAction() {
        trainerBox.setDisable(true);
        trainerBox.setVisible(false);
        addTrainerBox.setDisable(false);
        addTrainerBox.setVisible(true);
    }

    @FXML void addTrainerAction() {
        addTrainerBox.setDisable(true);
        addTrainerBox.setVisible(false);
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
