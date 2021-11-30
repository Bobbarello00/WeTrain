package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.TimeSchedulerController;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NewCourseController {
    @FXML
    private Button createCourseButt;
    @FXML
    private Button createWorkoutButt;
    @FXML
    private Button editButt;
    @FXML
    private ListView<?> exercisesSelectedList;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLessonsButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    private Button mondayButton;
    @FXML
    private TimeSchedulerController mondayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button tuesdayButton;
    @FXML
    private TimeSchedulerController tuesdayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button wednesdayButton;
    @FXML
    private TimeSchedulerController wednesdayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button thursdayButton;
    @FXML
    private TimeSchedulerController thursdayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button fridayButton;
    @FXML
    private TimeSchedulerController fridayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button saturdayButton;
    @FXML
    private TimeSchedulerController saturdayTimeScheduler = new TimeSchedulerController();
    @FXML
    private Button sundayButton;
    @FXML
    private TimeSchedulerController sundayTimeScheduler = new TimeSchedulerController();
    @FXML
    private TextField workoutNameText;
    @FXML
    void createCourseButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NewWorkoutPlan", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void editButtonAction() {

    }
    @FXML
    void logoAction() {

    }
    @FXML
    void logoutButtonAction() {

    }
    @FXML
    void manageLessonsButtonAction() {

    }
    @FXML
    void manageRequestsButtonAction() {

    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> mondayTimeScheduler.toggleVisibility();
            case "tuesdayButton" -> tuesdayTimeScheduler.toggleVisibility();
            case "wednesdayButton" -> wednesdayTimeScheduler.toggleVisibility();
            case "thursdayButton" -> thursdayTimeScheduler.toggleVisibility();
            case "fridayButton" -> fridayTimeScheduler.toggleVisibility();
            case "saturdayButton" -> saturdayTimeScheduler.toggleVisibility();
            case "sundayButton" -> sundayTimeScheduler.toggleVisibility();
        }
    }
}
