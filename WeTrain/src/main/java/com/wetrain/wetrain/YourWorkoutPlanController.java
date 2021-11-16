package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class YourWorkoutPlanController {

    @FXML
    private Button bookButt;

    @FXML
    private Button buyButt;

    @FXML
    private Button dietButt;

    @FXML
    private Button editButt;

    @FXML
    private Button logoutButt;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button workoutButt;

    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthletesHome");
        mainPane.setCenter(view);
    }
    @FXML
    void bookButtonAction() {System.out.println("Book private lesson");}
    @FXML
    void buyButtonAction() {System.out.println("Buy Monthly subscription");}
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourDietPlan");
        mainPane.setCenter(view);
    }

    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourWorkoutPlan");
        mainPane.setCenter(view);
    }

}
