package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import viewone.ListPopulate;
import viewone.MainPane;
import viewone.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutRequestsController extends HomeControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    @FXML
    private ListView<Node> requestsList;
    @FXML
    void newWorkoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan",HOME);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,requestsList);
    }
}
