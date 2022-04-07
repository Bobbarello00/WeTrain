package viewone.graphical_controllers.trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewWorkoutPlanGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    public final DaysOfTheWeekController daysController = new DaysOfTheWeekController();

    @FXML private ListView<Node> exercisesList;
    @FXML private ListView<Node> exercisesSelectedList;
    @FXML private Button createButton;
    @FXML private Text usernameText1;

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

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,exercisesList);
        ListPopulate.populateList(10,exercisesSelectedList);
        setUsername();
    }
}
