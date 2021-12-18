package com.wetrain.wetrain.controllers.nutritionists;

import com.wetrain.wetrain.DaysOfTheWeekController;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewDietController {

    private final DaysOfTheWeekController daysController = new DaysOfTheWeekController();
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
    private Button createButton;
    @FXML
    private Button manageDietsButton;
    @FXML
    private Button yourCollaboratorButton;
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
    void createButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "nutritionists");
        System.out.println("Created");
     }
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(editButton, "YourProfileTrainersNutritionists", "", false);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
}
