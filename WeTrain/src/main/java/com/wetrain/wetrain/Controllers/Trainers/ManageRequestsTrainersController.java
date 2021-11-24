package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageRequestsTrainersController implements Initializable {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLessonsButt;
    @FXML
    private ListView requestsList;
    @FXML
    private Button createCourseButt;
    @FXML
    private Button createWorkoutButt;
    @FXML
    private Button editButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButt, "Launcher/WeTrainGUI");}
    @FXML
    void manageLessonsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageLessonsTrainers", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void createCourseButtonAction() {System.out.println("Create New Course");}
    @FXML
    void createWorkoutButtonAction() {System.out.println("Create New Workout");}
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void editButtonAction() {System.out.println("Edit");}
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,requestsList,true);
    }
}
