package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import controller.WorkoutPlanController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.WorkoutPlan;
import viewone.bean.CourseBean;
import viewone.bean.WorkoutPlanBean;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourWeeklyScheduleGUIController extends HomeGUIControllerAthletes implements Initializable {

    private Button previousButton;
    private Text previousText;
    @FXML private Button mondayButton;
    @FXML private Text mondayText;
    @FXML private Button tuesdayButton;
    @FXML private Text tuesdayText;
    @FXML private Button wednesdayButton;
    @FXML private Text wednesdayText;
    @FXML private Button thursdayButton;
    @FXML private Text thursdayText;
    @FXML private Button fridayButton;
    @FXML private Text fridayText;
    @FXML private Button saturdayButton;
    @FXML private Text saturdayText;
    @FXML private Button sundayButton;
    @FXML private Text sundayText;

    private final CourseManagementAthleteController courseManagementAthleteController = new CourseManagementAthleteController();
    private final WorkoutPlanController workoutPlanController = new WorkoutPlanController();

    private void colorShift(Button button, Text text){
        if(previousButton!=null){
            previousButton.setStyle(null);
            previousText.setStyle("-fx-fill: white");
        }
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-color:  rgb(24, 147, 21);" +
                "-fx-border-radius: 50");
        text.setStyle("-fx-fill: rgb(24, 147, 21)");
        previousButton = button;
        previousText = text;
    }
    @FXML void dayButtonAction(ActionEvent event) throws SQLException {
        colorShift((Button) event.getSource(), ((Text)((Button) event.getSource()).getChildrenUnmodifiable().get(0)));
        //TODO Possiamo togliere CourseEssentialBean?
        //List<CourseBean> courseBeanList = courseManagementAthleteController.getCourseList();
        WorkoutPlanBean workoutPlanBean = workoutPlanController.getWorkoutPlan();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserInfoTab();
    }

}
