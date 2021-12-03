package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

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
    private BorderPane mainPane;
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
    @FXML
    void appointmentRequestButtonAction() {

    }
    @FXML
    void consultationRequestButtonAction() {

    }
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"YourDietPlan", "Athletes");
    }
    @FXML
    void dietRequestButtonAction() {

    }
    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"FindCourse", "Athletes");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"AthletesHome", "Athletes");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);
    }
    @FXML
    void nutritionistCertificationsButtonAction() {

    }
    @FXML
    void staffButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"AthletesHome", "Athletes");
    }
    @FXML
    void trainerCertificationsButtonAction() {

    }
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"YourWorkoutPlan", "Athletes");
    }
    @FXML
    void workoutRequestButtonAction() {

    }
}
