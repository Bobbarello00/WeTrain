package viewone.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.PageSwitchSizeChange;
import viewone.bean.DayBean;
import viewone.bean.ExerciseBean;
import viewone.bean.ExerciseForWorkoutPlanBean;
import viewone.bean.WorkoutDayBean;
import viewone.engeneering.AlertGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ExerciseOverviewGUIController{

    @FXML private TextArea infoTextArea;
    @FXML private TextField nameText;
    @FXML private Button addButton;

    private ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean;
    private boolean alreadyAdded = false;

    private NewWorkoutPlanGUIController newWorkoutPlanGUIController;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    @FXML void addOrRemoveAction(ActionEvent event) {
        try{
            if (!alreadyAdded) {
                    satisfyWorkoutRequestsController.addExerciseToWorkoutDay(exerciseForWorkoutPlanBean);
            } else {
                    satisfyWorkoutRequestsController.removeExerciseFromWorkoutDay(exerciseForWorkoutPlanBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return;
        } catch (ElementNotFoundException e) {
            return;
        }
        newWorkoutPlanGUIController.updateSelectedExerciseList();
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void deleteAction(ActionEvent event) {
        try {
            satisfyWorkoutRequestsController.removeExerciseFromTrainer(exerciseForWorkoutPlanBean);
            newWorkoutPlanGUIController.updateLists();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            return;
        }
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean) {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBean(exerciseForWorkoutPlanBean.getDay()));
        for (ExerciseBean exercise : workoutDayBean.getExerciseBeanList()) {
            if (Objects.equals(exercise.getName(), exerciseForWorkoutPlanBean.getName())) {
                return true;
            }
        }
        return false;
    }

    public void setValues(ExerciseForWorkoutPlanBean exerciseBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, NewWorkoutPlanGUIController newWorkoutPlanGUIController){
        this.exerciseForWorkoutPlanBean = exerciseBean;
        this.newWorkoutPlanGUIController = newWorkoutPlanGUIController;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        if(checkAlreadyAdded(exerciseForWorkoutPlanBean)){
            addButton.setStyle("-fx-background-color:  rgb(225, 100, 0)");
            addButton.setText("Remove from Selected Day");
            alreadyAdded = true;
        }
        nameText.setText(exerciseBean.getName());
        nameText.setEditable(false);
        infoTextArea.setText(exerciseBean.getInfo());
        infoTextArea.setEditable(false);
    }

}