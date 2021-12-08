package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.DaysOfTheWeekController;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewDietController {

    private DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    private Button createDietButton;
    @FXML
    private Button editButton;
    @FXML
    private Button fridayButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button manageAppointmentsButton;
    @FXML
    private Button manageDietsButton;
    @FXML
    private Button manageRequestsButton;
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
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "Nutritionists");
    }
    @FXML
    void editButtonAction() {
        System.out.println("Edit");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "Nutritionists");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher", true);
    }
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageAppointmentsNutritionists", "Nutritionists");
    }
    @FXML
    void manageDietsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageDietsNutritionists", "Nutritionists");
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageRequestsNutritionists", "Nutritionists");
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
}
