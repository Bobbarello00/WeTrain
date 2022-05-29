package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.NotificationsGUIController;
import viewtwo.graphical_controllers.PersonalInfoGUIController;

import java.io.IOException;

public class AthletesHomeGUIController {

    @FXML void notificationsButtonAction() throws IOException {
        NotificationsGUIController controller = (NotificationsGUIController) PageSwitchSimple.switchPage("Notifications", "");
        if(controller != null) {
            controller.setBackPath("AthletesHome", "athletes");
        }
    }

    @FXML void coursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", "athletes");
    }

    @FXML void logoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Login", "launcher");
    }



    @FXML void personalInfoButtonAction() throws IOException {
        PersonalInfoGUIController controller = (PersonalInfoGUIController) PageSwitchSimple.switchPage("PersonalInfo", "");
        if(controller != null) {
            controller.setBackPath("AthletesHome", "athletes");
        }
    }

    @FXML void trainerButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainerPage", "athletes");
    }

    @FXML void trainingButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

    @FXML void paymentMethodButtonAction() throws IOException {
        PageSwitchSimple.switchPage("PaymentMethod", "athletes");
    }
}
