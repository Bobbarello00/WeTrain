package viewone.graphical_controllers.athletes;

import viewone.DaysOfTheWeekController;
import viewone.graphical_controllers.ListPopulate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class YourWorkoutPlanController extends HomeControllerAthletes implements Initializable {

    private final DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    public Button mondayButton;
    @FXML
    public Button tuesdayButton;
    @FXML
    public Button wednesdayButton;
    @FXML
    public Button thursdayButton;
    @FXML
    public Button fridayButton;
    @FXML
    public Button saturdayButton;
    @FXML
    public Button sundayButton;
    @FXML
    private ListView<Node> exercisesList;
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,exercisesList,false);
    }
}
