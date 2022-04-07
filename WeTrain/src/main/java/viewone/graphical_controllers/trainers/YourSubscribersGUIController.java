package viewone.graphical_controllers.trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.ListPopulate;
import viewone.PageSwitchSizeChange;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourSubscribersGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private Label subscribersCountLabel;
    @FXML private ListView<Node> subscribersList;
    @FXML private Button writeEmailButton;
    @FXML private Text usernameText1;

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(),"EmailForm","",false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        int i = Integer.parseInt(subscribersCountLabel.getText());
        ListPopulate.populateList(i,subscribersList);
        setUsername();
    }
}
