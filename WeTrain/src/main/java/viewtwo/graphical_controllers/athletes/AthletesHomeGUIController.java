package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.NotificationsGUIController;
import viewtwo.graphical_controllers.PersonalInfoGUIController;

import java.io.IOException;

public class AthletesHomeGUIController {

    public static final String ATHLETES = "athletes";

    @FXML void notificationsButtonAction() throws IOException {
        NotificationsGUIController controller = (NotificationsGUIController) PageSwitchSimple.switchPage("Notifications", "");
        if(controller != null) {
            controller.setBackPath("AthletesHome", ATHLETES);
        }
    }

    @FXML void coursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", ATHLETES);
    }

    @FXML void logoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Login", "launcher");
    }



    @FXML void personalInfoButtonAction() throws IOException {
        PersonalInfoGUIController controller = (PersonalInfoGUIController) PageSwitchSimple.switchPage("PersonalInfo", "");
        if(controller != null) {
            controller.setBackPath("AthletesHome", ATHLETES);
        }
    }

    @FXML void trainerButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainerPage", ATHLETES);
    }

    @FXML void trainingButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", ATHLETES);
    }

    @FXML void paymentMethodButtonAction() throws IOException {
        PageSwitchSimple.switchPage("PaymentMethod", ATHLETES);
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE) {
            logoutButtonAction();
        }
    }
}
