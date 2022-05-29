package viewtwo.graphical_controllers.trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class ExerciseOverviewGUIController {

    @FXML private TextArea exerciseInfoTextArea;
    @FXML private TextField exerciseName;

    @FXML void addToPlanButtonAction(ActionEvent event) {

    }

    @FXML void backButtonAction(ActionEvent event) throws IOException {
        CreateWorkoutPlanGUIController controller = (CreateWorkoutPlanGUIController)PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController);
        }
    }

    @FXML void deleteExerciseButtonAction(ActionEvent event) {

    }

}
