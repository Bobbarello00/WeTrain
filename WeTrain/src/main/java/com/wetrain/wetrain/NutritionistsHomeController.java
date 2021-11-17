package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NutritionistsHomeController {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button editButt;
    @FXML
    private Button createDietButt;
    @FXML
    private Button manageAppointmentsButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    private Button manageDietsButt;
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome");
        mainPane.setCenter(view);
    }
    @FXML
    void manageAppointmentsButtonAction() {
        System.out.println("Manage Appointments");
    }
    @FXML
    void manageRequestsButtonAction() {
        System.out.println("Manage Requests");
    }
    @FXML
    void manageDietsButtonAction() {
        System.out.println("Manage Your Diets");
    }
    @FXML
    void editButtonAction() {
        System.out.println("Edit");
    }
    @FXML
    void createDietButtonAction() {
        System.out.println("Create New Diet");
    }
}
