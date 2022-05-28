package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class TrainerPageGUIController {

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void findTrainerButtonAction() throws IOException {
        //PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void sendEmailButtonAction() throws IOException {
        //PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void yourTrainerButtonAction() throws IOException {
        //PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

}