package viewone.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import controller.TrainerExercisesManagementController;
import exception.DBConnectionFailedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.bean.ExerciseBean;
import viewone.bean.ExerciseForWorkoutPlanBean;

import java.sql.SQLException;

public class ExerciseOverviewGUIController{

    @FXML private TextArea infoTextArea;
    @FXML private TextField nameText;
    @FXML private Button addButton;

    private ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean;
    private boolean alreadyAdded = false;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    private final TrainerExercisesManagementController trainerExercisesManagementController = new TrainerExercisesManagementController();

    @FXML void addOrRemoveAction(ActionEvent event) {
        //TODO come passiamo il workout day in cui và inserito/da cui và rimosso tale esercizio?
        if (!alreadyAdded) {
            satisfyWorkoutRequestsController.addExerciseToPlan(exerciseForWorkoutPlanBean);
        } else {
            satisfyWorkoutRequestsController.removeExerciseFromPlan(exerciseForWorkoutPlanBean);
        }
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void deleteAction(ActionEvent event) {
        try {
            trainerExercisesManagementController.removeExerciseFromTrainer(exerciseForWorkoutPlanBean.getExerciseBean());
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    public void setValues(ExerciseForWorkoutPlanBean bean){
        this.exerciseForWorkoutPlanBean = bean;

        if(satisfyWorkoutRequestsController.checkAlreadyAdded(exerciseForWorkoutPlanBean)){
            addButton.setStyle("-fx-background-color:  rgb(225, 100, 0)");
            addButton.setText("Remove from Plan");
            alreadyAdded = true;
        }

        ExerciseBean exerciseBean = exerciseForWorkoutPlanBean.getExerciseBean();
        nameText.setText(exerciseBean.getName());
        infoTextArea.setText(exerciseBean.getInfo());
    }

}