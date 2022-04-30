package viewone.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import controller.TrainerExercisesManagementController;
import exception.DBConnectionFailedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.WeTrain;
import viewone.bean.ExerciseForWorkoutPlanBean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
        try{
            if (!alreadyAdded) {
                    satisfyWorkoutRequestsController.addExerciseToPlan(exerciseForWorkoutPlanBean);
            } else {
                    satisfyWorkoutRequestsController.removeExerciseFromPlan(exerciseForWorkoutPlanBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
            return;
        }
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void deleteAction(ActionEvent event) {
        try {
            trainerExercisesManagementController.removeExerciseFromTrainer(exerciseForWorkoutPlanBean);
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

    public void setValues(ExerciseForWorkoutPlanBean exerciseBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController){
        this.exerciseForWorkoutPlanBean = exerciseBean;

        if(satisfyWorkoutRequestsController.checkAlreadyAdded(exerciseForWorkoutPlanBean)){
            addButton.setStyle("-fx-background-color:  rgb(225, 100, 0)");
            addButton.setText("Remove from Plan");
            alreadyAdded = true;
        }

        nameText.setText(exerciseBean.getName());
        infoTextArea.setText(exerciseBean.getInfo());
    }

}