package viewone.graphical_controllers.trainers;

import viewone.graphical_controllers.HomeControllerTrainersNutritionists;
import viewone.graphical_controllers.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageLessonsTrainersController extends HomeControllerTrainersNutritionists implements Initializable {
    @FXML
    private ListView<Node> coursesList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList,true);
    }
}

