package viewone.graphical_controllers.trainers;

import controller.TrainerExercisesManagementController;
import exception.DBConnectionFailedException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.DaysOfTheWeekButtonController;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseBean;
import viewone.bean.ExerciseBean;
import viewone.bean.RequestBean;
import viewone.list_cell_factories.ExerciseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class NewWorkoutPlanGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    @FXML private ListView<ExerciseBean> exercisesList;
    @FXML private ListView<ExerciseBean> exercisesSelectedList;
    @FXML private Button createButton;

    private RequestBean requestBean;
    private final TrainerExercisesManagementController trainerExercisesManagementController = new TrainerExercisesManagementController();

    @FXML public void addExerciseTextAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(createButton, "AddExercise", HOME, false);
    }

    @FXML void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }

    @FXML void navigationButtonAction(ActionEvent event) throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WorkoutRequests", HOME);
        if(event.getSource()==createButton) {
            System.out.println("Created");
        }
    }

    public void setRequest(RequestBean request) {
        requestBean = request;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        exercisesList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        exercisesSelectedList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        try {
            ObservableList<ExerciseBean> exerciseBeanObservableList = FXCollections.observableList(trainerExercisesManagementController.getTrainerExercises());
            exercisesList.setItems(exerciseBeanObservableList);
            exercisesList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                            try {
                                //TODO EXERCISE OVERVIEW
                                if(newItem != null){
                                    ExerciseOverviewGUIController exerciseOverviewGUIController =
                                            (ExerciseOverviewGUIController) PageSwitchSizeChange.pageSwitch(logoutButton,
                                            "ExerciseOverview",
                                            "trainers",
                                            false);
                                    exerciseOverviewGUIController.setValues(newItem);
                                    Platform.runLater(() -> exercisesList.getSelectionModel().clearSelection());
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
            setUserInfoTab();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }

    }

}
