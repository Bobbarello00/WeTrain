package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TrainersHomeController {
    @FXML
    private Button calendarButt;
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLButt;
    @FXML
    private Button createButt;
    @FXML
    private Button startButt;
    @FXML
    private Button editButt;
    @FXML
    private Button manageRButt;
    @FXML
    private Text trainersHomeButt;
    @FXML
    void calendarButtonAction() {System.out.println("Lesson Plan Calendar");}
    @FXML
    void calendarButtonEntered() {calendarButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void calendarButtonExited() {calendarButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void logoutButtonEntered() {logoutButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void logoutButtonExited() {logoutButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void manageLButtonAction() {System.out.println("Manage Lessons");}
    @FXML
    void manageLButtonEntered() {manageLButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void manageLButtonExited() {manageLButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void createButtonAction() {System.out.println("Create New Course");}
    @FXML
    void createButtonEntered() {createButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void createButtonExited() {createButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void startButtonAction() {System.out.println("Start Lesson");}
    @FXML
    void startButtonEntered() {startButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void startButtonExited() {startButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void manageRButtonAction() {System.out.println("Manage Requests");}
    @FXML
    void manageRButtonEntered() {manageRButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void manageRButtonExited() {manageRButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void editButtonAction() {System.out.println("Edit");}
    @FXML
    void editButtonEntered() {editButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void editButtonExited() {editButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void trainersHomeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome");
        mainPane.setCenter(view);
    }
}
