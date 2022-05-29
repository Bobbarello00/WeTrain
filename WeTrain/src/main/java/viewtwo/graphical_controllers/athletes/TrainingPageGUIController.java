package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class TrainingPageGUIController {

    public static final String ATHLETES = "athletes";

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", ATHLETES);
    }

    @FXML void requestWorkoutPlanButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestForm", ATHLETES);
    }

    @FXML void weeklyScheduleButtonAction() throws IOException {
        PageSwitchSimple.switchPage("WeeklySchedule", ATHLETES);
    }

    @FXML void yourWorkoutPlanButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourWorkoutPlan", ATHLETES);
    }
}