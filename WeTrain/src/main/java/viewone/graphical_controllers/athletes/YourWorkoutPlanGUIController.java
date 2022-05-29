package viewone.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import viewone.DaysOfTheWeekButtonController;
import viewone.PageSwitchSizeChange;
import viewone.bean.ExerciseBean;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;
import engeneering.manage_list.list_cell_factories.ExerciseListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourWorkoutPlanGUIController extends HomeGUIControllerAthletes implements Initializable {

    private final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();
    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();
    private WorkoutPlanBean workoutPlanBean;

    @FXML public Button mondayButton;
    @FXML public Button tuesdayButton;
    @FXML public Button wednesdayButton;
    @FXML public Button thursdayButton;
    @FXML public Button fridayButton;
    @FXML public Button saturdayButton;
    @FXML public Button sundayButton;
    @FXML private ListView<ExerciseBean> exerciseList;
    @FXML private Label infoLabel;

    @FXML void dayButtonAction(ActionEvent event) {
        String day = daysController.dayButtonAction(event);
        if(workoutPlanBean == null){
            return;
        }
        for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
            if(Objects.equals(workoutDayBean.getDay(), day)){
                updateListForSelectedDay(workoutDayBean);
                return;
            }
        }
        updateListForSelectedDay(new WorkoutDayBean(day));
    }

    private void updateListForSelectedDay(WorkoutDayBean workoutDayBean) {
        ObservableList<ExerciseBean> exerciseObservableList = FXCollections.observableList(workoutDayBean.getExerciseBeanList());
        exerciseList.setItems(FXCollections.observableList(exerciseObservableList));
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory(false));
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                        infoLabel.setText(String.format("""
                                Name: %s

                                Description:
                                \t\t\t %s

                                """, newItem.getName(),newItem.getInfo()));
                    }
                });
        setUserInfoTab();
        try {
            workoutPlanBean = requestWorkoutPlanController.getWorkoutPlan();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
    }
}
