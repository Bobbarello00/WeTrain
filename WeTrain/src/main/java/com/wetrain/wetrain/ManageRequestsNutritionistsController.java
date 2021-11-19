package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ManageRequestsNutritionistsController {

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
        Pane view = loader.getPage("NutritionistsHome");
        mainPane.setCenter(view);
    }

    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}

    @FXML
    void manageAppointmentsButtonAction() {

    }

    @FXML
    void manageDietsButtonAction() {

    }

    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome");
        mainPane.setCenter(view);
    }

}
