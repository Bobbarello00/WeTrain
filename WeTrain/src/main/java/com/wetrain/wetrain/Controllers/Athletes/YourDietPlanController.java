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
    private Button bookButt;
    @FXML
    private Button buyButt;
    @FXML
    private Button dietButt;
    @FXML
    private Button editButt;

    @FXML
    private VBox infoBox;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button mondayButt;
    @FXML
    private Text mondayText;
    @FXML
    private Button tuesdayButt;
    @FXML
    private Text tuesdayText;
    @FXML
    private Button wednesdayButt;
    @FXML
    private Text wednesdayText;
    @FXML
    private Button thursdayButt;
    @FXML
    private Text thursdayText;
    @FXML
    private Button fridayButt;
    @FXML
    private Text fridayText;
    @FXML
    private Button saturdayButt;
    @FXML
    private Text saturdayText;
    @FXML
    private Button sundayButt;
    @FXML
    private Text sundayText;
    @FXML
    private Button workoutButt;

    @FXML
    void bookButtonAction() {

    }

    @FXML
    void buyButtonAction() {

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
        PageSwitchSizeChange.pageSwitch(logoutButt, "Launcher/WeTrainGUI", true);}

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
    void mondayButtonAction() {colorShift(mondayButt, mondayText);}

    @FXML
    void tuesdayButtonAction() {colorShift(tuesdayButt, tuesdayText);}

    @FXML
    void wednesdayButtonAction() {colorShift(wednesdayButt, wednesdayText);}

    @FXML
    void thursdayButtonAction() {colorShift(thursdayButt, thursdayText);}

    @FXML
    void fridayButtonAction() {colorShift(fridayButt, fridayText);}

    @FXML
    void saturdayButtonAction() {colorShift(saturdayButt, saturdayText);}

    @FXML
    void sundayButtonAction() {colorShift(sundayButt, sundayText);}



}
