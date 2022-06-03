package viewtwo.graphical_controllers.trainers;

import beans.ExerciseBean;
import beans.RequestBean;
import beans.WorkoutDayBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.AlertGenerator;
import exceptions.DBUnreachableException;
import exceptions.ElementNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewtwo.PageSwitchSimple;
import viewtwo.beans_viewtwo.DayBeanB;
import viewtwo.beans_viewtwo.ExerciseForWorkoutPlanBeanB;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

public class ExerciseOverviewGUIController {

    @FXML private TextArea exerciseInfoTextArea;
    @FXML private TextField exerciseName;
    @FXML private Button addButton;

    private RequestBean selectedRequest;
    private ExerciseForWorkoutPlanBeanB exercise;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    private boolean alreadyAdded = false;
    private int day;

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBeanB exerciseForWorkoutPlanBean) {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanB(DayOfWeek.valueOf(exerciseForWorkoutPlanBean.getDay()).getValue()));
        for (ExerciseBean exerciseBean : workoutDayBean.getExerciseBeanList()) {
            if (Objects.equals(exerciseBean.getName(), exerciseForWorkoutPlanBean.getExerciseBean().getName())) {
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
            satisfyWorkoutRequestsController.removeExerciseFromTrainer(exercise.getExerciseBean());
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

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, ExerciseForWorkoutPlanBeanB exercise, int intDay) {
        selectedRequest = requestBean;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        this.exercise = exercise;
        day = intDay;
        if(checkAlreadyAdded(exercise)) {
            addButton.setText("Remove from Plan");
            alreadyAdded = true;
        }
        exerciseName.setText(exercise.getExerciseBean().getName());
        exerciseInfoTextArea.setText(exercise.getExerciseBean().getInfo());
    }
}
