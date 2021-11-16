package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AthletesHomeController {
    @FXML
    private Button bookButt;
    @FXML
    private Button buyButt;
    @FXML
    private Button dietButt;
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button startButt;
    @FXML
    private Button editButt;
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
    void dietButtonAction() {System.out.println("Your diet plan");}
    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void startButtonAction() {System.out.println("Start Workout");}
    @FXML
    void workoutButtonAction() {System.out.println("Your workout plan");}
}
