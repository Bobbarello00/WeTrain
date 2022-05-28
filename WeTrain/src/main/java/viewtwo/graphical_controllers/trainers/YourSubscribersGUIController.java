package viewtwo.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourSubscribersGUIController implements Initializable {

    @FXML private Pane masterDataPane;
    @FXML private TextArea masterDataTextArea;
    @FXML private ListView<?> subscriberList;

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", "trainers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
