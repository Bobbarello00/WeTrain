package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainersHomeController implements Initializable {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButton;
    @FXML
    private Button manageLessonsButton;
    @FXML
    private Button createCourseButton;
    @FXML
    private Button createWorkoutButton;
    @FXML
    private Button editButton;
    @FXML
    private ListView coursesList;
    @FXML
    private ListView requestsList;
    @FXML
    private Button manageRequestsButton;
    @FXML
    private Button mondayButton;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Button thursdayButton;
    @FXML
    private Button fridayButton;
    @FXML
    private Button saturdayButton;
    @FXML
    private Button sundayButton;
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher", true);
    }
    @FXML
    void manageLessonsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageLessonsTrainers", "Trainers");
    }
    @FXML
    void createCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewCourse", "Trainers");
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan", "Trainers");
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageRequestsTrainers", "Trainers");
    }
    @FXML
    void editButtonAction() {System.out.println("Edit");}
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "Trainers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList,true);
        ListPopulate.populateList(10,requestsList,true);
    }
}
