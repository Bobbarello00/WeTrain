package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class YourPersonalStaffController {

    @FXML
    private Button appointmentRequestButton;
    @FXML
    private Button consultationRequestButton;
    @FXML
    private Button dietButton;
    @FXML
    private Button dietRequestButton;
    @FXML
    private Button editButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button nutritionistCertificationsButton;
    @FXML
    private Label nutritionistName;
    @FXML
    private Button staffButton;
    @FXML
    private Button trainerCertificationsButton;
    @FXML
    private Label trainerName;
    @FXML
    private Button workoutButton;
    @FXML
    private Button workoutRequestButton;
    //TODO Implementare metodi
    @FXML
    void trainerCertificationsButtonAction() {

    }
    @FXML
    void workoutRequestButtonAction() {

    }
    @FXML
    void nutritionistCertificationsButtonAction() {

    }
    @FXML
    void appointmentRequestButtonAction() {

    }
    @FXML
    void consultationRequestButtonAction() {

    }
    @FXML
    void dietRequestButtonAction() {

    }
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourDietPlan", "Athletes");
    }
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(editButton, "YourProfileAthletes", "Athletes", false);
    }
    @FXML
    void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"FindCourse", "Athletes");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", "Athletes");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher",true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
    @FXML
    void staffButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", "Athletes");
    }
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourWorkoutPlan", "Athletes");
    }
}
