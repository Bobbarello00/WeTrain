package com.wetrain.wetrain.controllers.athletes;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class YourPersonalStaffController {
    private static final String HOME = "athletes";
    @FXML
    private Button logoutButton;
    @FXML
    private Label nutritionistName;
    @FXML
    private Label trainerName;
    //TODO Implementare metodi
    @FXML
    void workoutRequestButtonAction() {

    }
    @FXML
    void dietRequestButtonAction() {

    }
    @FXML
    void appointmentRequestButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "RequestForm", HOME, false);
    }
    @FXML
    void consultationRequestButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "RequestForm", HOME, false);
    }
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "YourProfileAthletes", HOME, false);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher",true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
}
