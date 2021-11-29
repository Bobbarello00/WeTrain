package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NewDietController {

    @FXML
    private Button createDietButt;

    @FXML
    private Button editButt;

    @FXML
    private Button fridayButton;

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
    private Button mondayButton;

    @FXML
    private Button saturdayButton;

    @FXML
    private Button sundayButton;

    @FXML
    private Button thursdayButton;

    @FXML
    private Button tuesdayButton;

    @FXML
    private Button wednesdayButton;

    @FXML
    private TextField workoutNameText;

    @FXML
    void createDietButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }

    @FXML
    void editButtonAction() {
        System.out.println("Edit");
    }

    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }

    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButt, "Launcher/WeTrainGUI", true);}

    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageAppointmentsNutritionists", "Nutritionists");
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

    @FXML
    void mondayButtonAction() {

    }
    @FXML
    void tuesdayButtonAction() {

    }
    @FXML
    void wednesdayButtonAction() {

    }
    @FXML
    void thursdayButtonAction() {

    }
    @FXML
    void fridayButtonAction(ActionEvent event) {

    }
    @FXML
    void saturdayButtonAction() {

    }
    @FXML
    void sundayButtonAction() {

    }
}
