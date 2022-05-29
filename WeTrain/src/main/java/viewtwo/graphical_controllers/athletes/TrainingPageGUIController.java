package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class TrainingPageGUIController {

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void requestWorkoutPlanButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestForm", "athletes");
    }

    @FXML void weeklyScheduleButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourWeeklySchedule", "athletes");
    }

    @FXML void yourWorkoutPlanButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourWorkoutPlan", "athletes");
    }
}