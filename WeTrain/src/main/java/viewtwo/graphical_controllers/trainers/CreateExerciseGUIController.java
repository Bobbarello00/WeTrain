package viewtwo.graphical_controllers.trainers;

import controllers.SatisfyWorkoutRequestsController;
import engeneering.AlertGenerator;
import exceptions.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.beans.ExerciseBean;
import viewone.beans.RequestBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreateExerciseGUIController {

    @FXML private TextArea exerciseInfoTextArea;
    @FXML private TextField exerciseNameText;

    private RequestBean selectedRequest;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    private int day;

    @FXML void createExerciseButtonAction() throws IOException {
        ExerciseBean exerciseBean = new ExerciseBean(
                exerciseNameText.getText(),
                exerciseInfoTextArea.getText());
        try {
            satisfyWorkoutRequestsController.addExerciseToTrainer(exerciseBean);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        backAction();
    }

    private void backAction() throws IOException {
        CreateWorkoutPlanGUIController controller = (CreateWorkoutPlanGUIController) PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, day);
        }
    }

    @FXML void cancelButtonAction() throws IOException {
        backAction();
    }

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, int intDay) {
        this.selectedRequest = requestBean;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        this.day = intDay;
    }
}
