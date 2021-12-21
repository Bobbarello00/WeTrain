package viewone.graphical_controllers.nutritionists;

import viewone.graphical_controllers.HomeControllerTrainersNutritionists;
import viewone.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistsHomeController extends HomeControllerTrainersNutritionists implements Initializable {
    @FXML
    private ListView<Node> dietsList;
    @FXML
    private ListView<Node> requestsList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,dietsList);
        ListPopulate.populateList(10,requestsList);
    }
}
