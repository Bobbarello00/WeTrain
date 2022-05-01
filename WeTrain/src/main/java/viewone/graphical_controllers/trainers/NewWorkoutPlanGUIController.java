package viewone.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import controller.TrainerExercisesManagementController;
import exception.DBConnectionFailedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.DaysOfTheWeekButtonController;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.DayBean;
import viewone.bean.ExerciseBean;
import viewone.bean.RequestBean;
import viewone.bean.WorkoutDayBean;
import viewone.engeneering.ManageExerciseList;
import viewone.list_cell_factories.ExerciseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class NewWorkoutPlanGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    @FXML private ListView<ExerciseBean> exerciseList;
    @FXML private ListView<ExerciseBean> selectedExerciseList;
    @FXML private Button mondayButton;
    @FXML private Button createButton;

    private RequestBean requestBean;
    private final TrainerExercisesManagementController trainerExercisesManagementController = new TrainerExercisesManagementController();
    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();

    //TODO Observer per esercizi selezionati

    @FXML public void addExerciseTextAction() throws IOException {
        CreateNewExerciseGUIController controller = (CreateNewExerciseGUIController) PageSwitchSizeChange.pageSwitch(createButton, "CreateNewExercise", HOME, false);
        controller.setValue(this);
    }

    @FXML void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
        updateSelectedExerciseList();
    }

    @FXML void cancelButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WorkoutRequests", HOME);
    }

    @FXML void createButtonAction() throws IOException {
        try {
            satisfyWorkoutRequestsController.sendWorkoutRequest(requestBean);
            PageSwitchSimple.switchPage(MainPane.getInstance(),"WorkoutRequests", HOME);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRequest(RequestBean request) {
        requestBean = request;
    }

    public void updateSelectedExerciseList() {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBean(daysController.getDay()));
        System.out.println("size: "+ workoutDayBean.getExerciseBeanList().size());
        ManageExerciseList.updateList(
                selectedExerciseList,
                workoutDayBean.getExerciseBeanList());
    }

    public void updateExerciseList() throws SQLException, DBConnectionFailedException {
        ManageExerciseList.updateList(
                exerciseList,
                trainerExercisesManagementController.getTrainerExercises()
        );
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        mondayButton.fire();
        exerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        selectedExerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        try {

            ManageExerciseList.setListener(exerciseList, daysController, satisfyWorkoutRequestsController, this);
            ManageExerciseList.setListener(selectedExerciseList, daysController, satisfyWorkoutRequestsController, this);
            List<ExerciseBean> exerciseBeanList = trainerExercisesManagementController.getTrainerExercises();
            ManageExerciseList.updateList(exerciseList, exerciseBeanList);
            setUserInfoTab();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }

    }

}
