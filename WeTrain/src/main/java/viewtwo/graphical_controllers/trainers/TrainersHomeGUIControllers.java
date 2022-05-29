package viewtwo.graphical_controllers.trainers;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.NotificationsGUIController;
import viewtwo.graphical_controllers.PersonalInfoGUIController;

import java.io.IOException;

public class TrainersHomeGUIControllers {

    public static final String TRAINERS = "trainers";

    @FXML void coursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("ManageCourses", TRAINERS);
    }

    @FXML void logoutButtonAction() {
        PageSwitchSimple.logOff();
    }

    @FXML void notificationsButtonAction() throws IOException {
        NotificationsGUIController controller = (NotificationsGUIController) PageSwitchSimple.switchPage("Notifications", "");
        if(controller != null) {
            controller.setBackPath("TrainersHome", TRAINERS);
        }
    }

    @FXML void paymentMethodButtonAction() throws IOException {
        PageSwitchSimple.switchPage("GetPaidPage", TRAINERS);
    }

    @FXML void personalInfoButtonAction() throws IOException {
        PersonalInfoGUIController controller = (PersonalInfoGUIController) PageSwitchSimple.switchPage("PersonalInfo", "");
        if(controller != null) {
            controller.setBackPath("TrainersHome", TRAINERS);
        }
    }

    @FXML void requestsButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestsPage", TRAINERS);
    }

    @FXML void subscribersButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourSubscribers", TRAINERS);
    }

}
