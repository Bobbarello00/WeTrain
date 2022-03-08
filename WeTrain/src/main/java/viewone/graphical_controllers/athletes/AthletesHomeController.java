package viewone.graphical_controllers.athletes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import viewone.ListPopulate;

import java.net.URL;
import java.util.ResourceBundle;

public class AthletesHomeController extends HomeControllerAthletes implements Initializable {
    @FXML
    private ListView<Node> coursesList;
    @FXML
    private ListView<Node> popularsList;
    @FXML
    private ListView<Node> feedList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList);
        ListPopulate.populateList(10,popularsList);
        ListPopulate.populateList(30,feedList);
        coursesList.getSelectionModel().getSelectedItems();
        //TODO
        coursesList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<Node>() {
                    @Override
                    public void changed(ObservableValue<? extends Node> observableValue, Node oldNode, Node newNode) {
                        Label label = (Label)(newNode.lookup("#itemCode"));
                        label.setText("Ciao");
                    }
                });
    }
}
