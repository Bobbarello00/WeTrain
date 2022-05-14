package viewone.graphical_controllers.trainers;

import controller.TrainerExercisesManagementController;
import exception.DBUnreachableException;
import viewone.MainPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.PageSwitchSizeChange;
import viewone.bean.ExerciseBean;
import viewone.engeneering.AlertFactory;

import java.sql.SQLException;
import java.util.List;

public class CreateNewExerciseGUIController {
    @FXML private TextArea descriptionTextArea;
    @FXML private TextField nameText;

    private final TrainerExercisesManagementController trainerExercisesManagementController = new TrainerExercisesManagementController();
    private NewWorkoutPlanGUIController newWorkoutPlanGUIController;

    @FXML void createButtonAction(ActionEvent event) {
        ExerciseBean exerciseBean = new ExerciseBean(
                nameText.getText(),
                descriptionTextArea.getText());
        try {
            trainerExercisesManagementController.addExerciseToTrainer(exerciseBean);
            newWorkoutPlanGUIController.updateExerciseList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertFactory.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
        cancelButtonAction(event);
    }

    @FXML void cancelButtonAction(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    public void setValue(NewWorkoutPlanGUIController newWorkoutPlanGUIController) {
        this.newWorkoutPlanGUIController = newWorkoutPlanGUIController;
    }
}