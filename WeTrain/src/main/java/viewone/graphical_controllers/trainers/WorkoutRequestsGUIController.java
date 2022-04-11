package viewone.graphical_controllers.trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import viewone.ListPopulate;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutRequestsGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";

    @FXML private ListView<Node> requestsList;
    @FXML private Text usernameText1;

    @FXML void newWorkoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan",HOME);
    }

    @FXML void clarificationEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button)event.getSource(),"EmailForm","",false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,requestsList);
        setUsername();
    }
}
