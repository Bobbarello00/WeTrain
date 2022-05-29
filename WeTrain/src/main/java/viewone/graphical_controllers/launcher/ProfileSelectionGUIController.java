package viewone.graphical_controllers.launcher;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import engeneering.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProfileSelectionGUIController {
    private static final String HOME = "launcher";
    @FXML private Button athletesButton;

    @FXML protected void closeAction(){
        ((Stage) athletesButton.getScene().getWindow()).close();
    }

    @FXML protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }

    @FXML protected void athletesButtonAction() throws IOException {
        RegistrationGUIController registrationGUIController = (RegistrationGUIController) Objects.requireNonNull(PageSwitchSimple.switchPage(MainPane.getInstance(),"AthleteRegistration", HOME));
        registrationGUIController.setSelectedProfile("Athlete");
    }

    @FXML protected void trainersButtonAction() throws IOException {
        RegistrationGUIController registrationGUIController = (RegistrationGUIController) Objects.requireNonNull(PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainerRegistration", HOME));
        registrationGUIController.setSelectedProfile("Trainer");
    }

    @FXML void buttonColorShiftEntered(MouseEvent event){
        ((Button) event.getSource()).setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }

    @FXML void buttonColorShiftExited(MouseEvent event){
        ((Button) event.getSource()).setStyle("-fx-background-color:  rgba(24, 147, 21, 0.65); -fx-background-radius: 25");
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            homeTextAction();
        }
    }
}
