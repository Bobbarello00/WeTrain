package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class TrainerPageGUIController {

    public static final String ATHLETES = "athletes";

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", ATHLETES);
    }

    @FXML void findTrainerButtonAction() throws IOException {
        PageSwitchSimple.switchPage("FindTrainer", ATHLETES);
    }

    @FXML void yourTrainerButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourTrainer", ATHLETES);
    }
}