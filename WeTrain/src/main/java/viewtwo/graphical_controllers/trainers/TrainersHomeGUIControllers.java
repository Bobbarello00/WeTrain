package viewtwo.graphical_controllers.trainers;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class TrainersHomeGUIControllers {

    @FXML void coursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("ManageCourses", "trainers");
    }

    @FXML void logoutButtonAction() {
        PageSwitchSimple.logOff();
    }

    @FXML void notificationsButtonAction() {

    }

    @FXML void paymentMethodButtonAction() throws IOException {
        PageSwitchSimple.switchPage("GetPaidPage", "trainers");
    }

    @FXML void personalInfoButtonAction() throws IOException {
        PersonalInfoGUIController controller = (PersonalInfoGUIController) PageSwitchSimple.switchPage("PersonalInfo", "");
        if(controller != null) {
            controller.setBackPath("TrainersHome", "trainers");
        }
    }

    @FXML void requestsButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestsPage", "trainers");
    }

    @FXML void subscribersButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourSubscribers", "trainers");
    }

}
