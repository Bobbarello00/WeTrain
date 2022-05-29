package viewtwo.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.bean.*;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ExerciseOverviewGUIController {

    @FXML private TextArea exerciseInfoTextArea;
    @FXML private TextField exerciseName;
    @FXML private Button addButton;

    private RequestBean selectedRequest;
    private ExerciseForWorkoutPlanBean exercise;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    private boolean alreadyAdded = false;
    private int day;

    public ExerciseOverviewGUIController() {}

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean) {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBean(exerciseForWorkoutPlanBean.getDay()));
        for (ExerciseBean exercise : workoutDayBean.getExerciseBeanList()) {
            if (Objects.equals(exercise.getName(), exerciseForWorkoutPlanBean.getName())) {
                return true;
            }
        }
        return false;
    }

    @FXML void addToPlanButtonAction() throws IOException {
        try{
            if (alreadyAdded) {
                satisfyWorkoutRequestsController.removeExerciseFromWorkoutDay(exercise);
            } else {
                satisfyWorkoutRequestsController.addExerciseToWorkoutDay(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
            return;
        } catch (ElementNotFoundException e) {
            return;
        }
        closeAction();
    }

    @FXML void backButtonAction() throws IOException {
        closeAction();
    }

    private void closeAction() throws IOException {
        CreateWorkoutPlanGUIController controller = (CreateWorkoutPlanGUIController) PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, day);
        }
    }

    @FXML void deleteExerciseButtonAction() throws IOException {
        try {
            satisfyWorkoutRequestsController.removeExerciseFromTrainer(exercise);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        closeAction();
    }

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, ExerciseForWorkoutPlanBean exercise, int intDay) {
        selectedRequest = requestBean;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        this.exercise = exercise;
        day = intDay;
        if(checkAlreadyAdded(exercise)) {
            addButton.setText("Remove from Plan");
            alreadyAdded = true;
        }
        exerciseName.setText(exercise.getName());
        exerciseInfoTextArea.setText(exercise.getInfo());
    }
}
