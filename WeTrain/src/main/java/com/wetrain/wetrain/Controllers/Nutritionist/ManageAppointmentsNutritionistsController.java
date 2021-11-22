package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.Controllers.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class ManageAppointmentsNutritionistsController {

    @FXML
    private Button createDietButt;

    @FXML
    private Button editButt;

    @FXML
    private Button logoutButt;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button manageAppointmentsButt;

    @FXML
    private Button manageDietsButt;

    @FXML
    private Button manageRequestsButt;

    @FXML
    void createDietButtonAction() {

    }

    @FXML
    void editButtonAction() {

    }

    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }

    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButt, "Launcher/WeTrainGUI");}

    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }

    @FXML
    void manageDietsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageDietsNutritionists", "Nutritionists");
        mainPane.setCenter(view);
    }

    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageRequestsNutritionists", "Nutritionists");
        mainPane.setCenter(view);
    }

}
