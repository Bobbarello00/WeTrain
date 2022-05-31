package viewone.graphical_controllers.trainers;

import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnreachableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import viewone.DaysOfTheWeekButtonController;
import engeneering.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.beans.*;
import engeneering.AlertGenerator;
import engeneering.manage_list.ManageExerciseList;
import engeneering.manage_list.list_cell_factories.ExerciseListCellFactory;

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
    @FXML private Button createButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button mondayButton;

    private RequestBean requestBean;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    @FXML public void searchButtonAction() {
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        ManageExerciseList.updateList(exerciseList, exerciseBeanList);
    }

    @FXML public void addExerciseTextAction() throws IOException {
        CreateNewExerciseGUIController controller = (CreateNewExerciseGUIController) PageSwitchSizeChange.pageSwitch(createButton, "CreateNewExercise", HOME, false);
        controller.setValue(this, satisfyWorkoutRequestsController);
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
            satisfyWorkoutRequestsController.sendWorkoutPlan(requestBean);
            PageSwitchSimple.switchPage(MainPane.getInstance(),"WorkoutRequests", HOME);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        ManageExerciseList.setListener(exerciseList, daysController, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListener(selectedExerciseList, daysController, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getTrainerExercises();
        ManageExerciseList.updateList(exerciseList, exerciseBeanList);
        setUserInfoTab();
        mondayButton.fire();
    }

    public void updateLists() throws DBUnreachableException, SQLException {
        updateExerciseList();
        updateSelectedExerciseList();
    }

    public void updateSelectedExerciseList() {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBean(daysController.getDay()));
        ManageExerciseList.updateList(
                selectedExerciseList,
                workoutDayBean.getExerciseBeanList());
    }

    public void updateExerciseList() throws SQLException, DBUnreachableException {
        ManageExerciseList.updateList(
                exerciseList,
                satisfyWorkoutRequestsController.getTrainerExercises()
        );
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory(false));
        selectedExerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory(false));
    }

}
