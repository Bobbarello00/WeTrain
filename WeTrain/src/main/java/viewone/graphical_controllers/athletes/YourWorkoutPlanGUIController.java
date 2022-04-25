package viewone.graphical_controllers.athletes;

import controller.WorkoutPlanController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import viewone.DaysOfTheWeekButtonController;
import viewone.ListPopulate;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourWorkoutPlanGUIController extends HomeGUIControllerAthletes implements Initializable {

    private final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();
    private final WorkoutPlanController workoutPlanController = new WorkoutPlanController();
    private WorkoutPlanBean workoutPlanBean;

    @FXML public Button mondayButton;
    @FXML public Button tuesdayButton;
    @FXML public Button wednesdayButton;
    @FXML public Button thursdayButton;
    @FXML public Button fridayButton;
    @FXML public Button saturdayButton;
    @FXML public Button sundayButton;
    @FXML private ListView<Node> exercisesList;
    @FXML private Label infoLabel;

    @FXML void dayButtonAction(ActionEvent event) {
        String day = daysController.dayButtonAction(event);
        for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
            if(Objects.equals(workoutDayBean.getDay(), day)){
                //TODO carica esercizi nella lista
                infoLabel.setText(workoutDayBean.getInfo());
            }
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,exercisesList);
        setUserInfoTab();
        try {
            workoutPlanBean = workoutPlanController.getWorkoutPlan();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
