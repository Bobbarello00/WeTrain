package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import viewone.ListPopulate;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainersHomeGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private ListView<Node> coursesList;
    @FXML private ListView<Node> requestsList;
    @FXML private Text usernameText;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList);
        ListPopulate.populateList(10,requestsList);
        setUsername();
    }

}
