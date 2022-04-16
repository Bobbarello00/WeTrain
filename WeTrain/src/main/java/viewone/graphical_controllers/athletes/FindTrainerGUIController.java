package viewone.graphical_controllers.athletes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.MainPane;

public class FindTrainerGUIController {

    @FXML private TextField trainerNameSearch;
    @FXML private Button searchButton;
    @FXML private ListView<?> trainersList;

    @FXML void closeAction() {
        ((Stage) searchButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void searchButtonAction(ActionEvent event) {

    }

}
