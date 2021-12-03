package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageDietsNutritionistsController implements Initializable{
    @FXML
    private Button createDietButton;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private ListView dietsList;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageAppointmentsButton;
    @FXML
    private Button manageDietsButton;
    @FXML
    private Button manageRequestsButton;
    @FXML
    void createDietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"NewDiet", "Nutritionists");
    }
    @FXML
    void editButtonAction() {

    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"NutritionistsHome", "Nutritionists");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);}
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"ManageAppointmentsNutritionists", "Nutritionists");
    }
    @FXML
    void manageDietsButtonAction()throws IOException {
        PageSwitchSimple.switchPage(mainPane,"NutritionistsHome", "Nutritionists");
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"ManageRequestsNutritionists", "Nutritionists");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,dietsList,true);
    }
}
