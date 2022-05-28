package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class AthletesHomeGUIController {

    @FXML void coursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("CoursesPage", "athletes");
    }

    @FXML void logoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Login", "launcher");
    }

    @FXML void notificationsButtonAction() {
        //TODO
    }

    @FXML void paymentMethodButtonAction() throws IOException {
        PageSwitchSimple.switchPage("PaymentMethod", "athletes");
    }

    @FXML void personalInfoButtonAction() {

    }

    @FXML void trainerButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainerPage", "athletes");
    }

    @FXML void trainingButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

}
