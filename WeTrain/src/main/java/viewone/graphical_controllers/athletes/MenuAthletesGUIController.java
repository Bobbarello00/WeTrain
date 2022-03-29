package viewone.graphical_controllers.athletes;

import viewone.ButtonBehavior;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuAthletesGUIController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "athletes";
    @FXML
    void trainerButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourPersonalTrainer",HOME);
    }
    @FXML
    void findCourseButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"FindCourse",HOME);
    }
    @FXML
    void weekButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourWeeklySchedule",HOME);
    }
    @FXML
    void workoutButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourWorkoutPlan",HOME);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", HOME);
        buttonBehavior.resetSelectedButton();
    }
}
