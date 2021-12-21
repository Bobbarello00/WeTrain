package com.wetrain.wetrain.graphical_controllers.nutritionists;

import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.graphical_controllers.HomeController;
import com.wetrain.wetrain.graphical_controllers.HomeControllerTrainersNutritionists;
import com.wetrain.wetrain.graphical_controllers.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageAppointmentsNutritionistsController  extends HomeControllerTrainersNutritionists implements Initializable{
    @FXML
    private ListView<Node> appointmentsList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,appointmentsList,true);
    }
}
