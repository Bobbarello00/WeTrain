package viewone.graphical_controllers.trainers;

import controller.TrainerExercisesManagementController;
import controller.WorkoutPlanController;
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

public class ExerciseOverviewGUIController{

    @FXML private TextArea infoTextArea;
    @FXML private TextField nameText;
    @FXML private Button addButton;

    private ExerciseBean exerciseBean;
    private boolean alreadyAdded = false;

    private final WorkoutPlanController workoutPlanController = new WorkoutPlanController();
    private final TrainerExercisesManagementController trainerExercisesManagementController = new TrainerExercisesManagementController();

    @FXML void addOrRemoveAction(ActionEvent event) {
        if(exerciseBean != null) {
            //TODO come passiamo il workout day in cui và inserito/da cui và rimosso tale esercizio?
            if (!alreadyAdded) {
                workoutPlanController.addExerciseToPlan(exerciseBean);
            } else {
                workoutPlanController.removeExerciseFromPlan(exerciseBean);
            }
        }
    }

    @FXML void deleteAction(ActionEvent event) {
        if(exerciseBean != null) {
            //TODO non dovrebbe mai essere null a questo punto... rimuovere il controllo?
            trainerExercisesManagementController.removeExerciseFromTrainer(exerciseBean);
        }
        ((Stage) addButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    public void setValues(ExerciseBean exerciseBean){
        try{
            if(workoutPlanController.checkAlreadyAdded(exerciseBean)){
                addButton.setStyle("-fx-background-color:  rgb(225, 100, 0)");
                addButton.setText("Remove from Plan");
                alreadyAdded = true;
            }
        }catch (){
            ((Stage) (addButton.getScene().getWindow())).close();
        }
        this.exerciseBean = exerciseBean;
        nameText.setText(exerciseBean.getName());
        infoTextArea.setText(exerciseBean.getInfo());
    }

}