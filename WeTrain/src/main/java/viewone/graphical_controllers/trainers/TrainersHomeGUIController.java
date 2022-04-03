package viewone.graphical_controllers.trainers;

import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainersHomeGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML
    private ListView<Node> coursesList;
    @FXML
    private ListView<Node> requestsList;
    @FXML
    private Text usernameText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList);
        ListPopulate.populateList(10,requestsList);
        usernameText.setText(LoggedUserSingleton.getInstance().getUsername());
    }

}
