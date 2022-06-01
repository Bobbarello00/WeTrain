package viewone.graphical_controllers.trainers;

import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnreachableException;
import exceptions.ElementNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import engeneering.MainPane;
import viewone.PageSwitchSizeChange;
import viewone.beans_viewone.DayBeanA;
import beans.ExerciseBean;
import viewone.beans_viewone.ExerciseForWorkoutPlanBeanA;
import beans.WorkoutDayBean;
import engeneering.AlertGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ExerciseOverviewGUIController {

    @FXML private TextArea infoTextArea;
    @FXML private TextField nameText;
    @FXML private Button addButton;

    private ExerciseForWorkoutPlanBeanA exerciseForWorkoutPlanBean;
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
            satisfyWorkoutRequestsController.removeExerciseFromTrainer(exerciseForWorkoutPlanBean.getExerciseBean());
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

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBeanA exerciseForWorkoutPlanBean) {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanA(exerciseForWorkoutPlanBean.getDay()));
        for (ExerciseBean exercise : workoutDayBean.getExerciseBeanList()) {
            if (Objects.equals(exercise.getName(), exerciseForWorkoutPlanBean.getExerciseBean().getName())) {
                return true;
            }
        }
        return false;
    }

    public void setValues(ExerciseForWorkoutPlanBeanA exerciseBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, NewWorkoutPlanGUIController newWorkoutPlanGUIController){
        this.exerciseForWorkoutPlanBean = exerciseBean;
        this.newWorkoutPlanGUIController = newWorkoutPlanGUIController;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        if(checkAlreadyAdded(exerciseForWorkoutPlanBean)){
            addButton.setStyle("-fx-background-color:  rgb(225, 100, 0)");
            addButton.setText("Remove from Selected Day");
            alreadyAdded = true;
        }
        nameText.setText(exerciseBean.getExerciseBean().getName());
        nameText.setEditable(false);
        infoTextArea.setText(exerciseBean.getExerciseBean().getInfo());
        infoTextArea.setEditable(false);
    }

}