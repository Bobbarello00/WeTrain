package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AthletesHomeController implements Initializable {
    @FXML
    private Button staffButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button dietButton;
    @FXML
    private Button logoutButton;
    @FXML
    private ListView<?> coursesList;
    @FXML
    private ListView<?> popularsList;
    @FXML
    private Button editButton;
    @FXML
    private Button workoutButton;
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(editButton, "YourProfileAthletes", "Athletes", false);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", "Athletes");
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
    @FXML
    void staffButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourPersonalStaff", "Athletes");
    }
    @FXML
    void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"FindCourse", "Athletes");
    }
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourDietPlan", "Athletes");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher", true);
    }
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourWorkoutPlan", "Athletes");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList,true);
        ListPopulate.populateList(10,popularsList,false);
    }
}
