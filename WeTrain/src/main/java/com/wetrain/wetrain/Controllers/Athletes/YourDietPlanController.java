package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class YourDietPlanController {

    private Button previousButt;
    private Text previousText;
    @FXML
    private Button bookButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button dietButton;
    @FXML
    private Button editButton;

    @FXML
    private VBox infoBox;
    @FXML
    private Button logoutButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button mondayButton;
    @FXML
    private Text mondayText;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Text tuesdayText;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Text wednesdayText;
    @FXML
    private Button thursdayButton;
    @FXML
    private Text thursdayText;
    @FXML
    private Button fridayButton;
    @FXML
    private Text fridayText;
    @FXML
    private Button saturdayButton;
    @FXML
    private Text saturdayText;
    @FXML
    private Button sundayButton;
    @FXML
    private Text sundayText;
    @FXML
    private Button workoutButton;

    @FXML
    void bookButtonAction() {

    }

    @FXML
    void findCourseButtonAction() {

    }

    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthletesHome", "Athletes");
        mainPane.setCenter(view);
    }

    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourWorkoutPlan", "Athletes");
        mainPane.setCenter(view);
    }

    @FXML
    void editButtonAction() {

    }

    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthletesHome", "Athletes");
        mainPane.setCenter(view);
    }

    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);
    }

    private void colorShift(Button button, Text text){
        if(previousButt!=null){
            previousButt.setStyle(null);
            previousText.setStyle("-fx-fill: white");
        }
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-color:  rgb(24, 147, 21);" +
                "-fx-border-radius: 50");
        text.setStyle("-fx-fill: rgb(24, 147, 21)");
        previousButt = button;
        previousText = text;
    }

    @FXML
    void mondayButtonAction() {colorShift(mondayButton, mondayText);}

    @FXML
    void tuesdayButtonAction() {colorShift(tuesdayButton, tuesdayText);}

    @FXML
    void wednesdayButtonAction() {colorShift(wednesdayButton, wednesdayText);}

    @FXML
    void thursdayButtonAction() {colorShift(thursdayButton, thursdayText);}

    @FXML
    void fridayButtonAction() {colorShift(fridayButton, fridayText);}

    @FXML
    void saturdayButtonAction() {colorShift(saturdayButton, saturdayText);}

    @FXML
    void sundayButtonAction() {colorShift(sundayButton, sundayText);}



}
