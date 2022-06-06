package start;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartGUIController implements Initializable {

    @FXML private RadioButton viewoneSelection;
    @FXML private RadioButton viewtwoSelection;

    public void nextAction(ActionEvent event) throws Exception {
        Application application;
        if(viewoneSelection.isSelected()) {
            application = new viewone.WeTrain();
        } else {
            application = new viewtwo.WeTrain();
        }
        application.start(new Stage());
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        viewoneSelection.setToggleGroup(group);
        viewtwoSelection.setToggleGroup(group);
        viewoneSelection.fire();
    }
}
