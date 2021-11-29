package com.wetrain.wetrain.Controllers.Trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    private TextField workoutNameText;
    @FXML
    void createCourseButtonAction() {

    }
    @FXML
    void createWorkoutButtonAction() {

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
            case "mondayButton" -> System.out.println("monday");
            case "tuesdayButton" -> System.out.println("tuesday");
            case "wednesdayButton" -> System.out.println("wednesday");
            case "thursdayButton" -> System.out.println("thursday");
            case "fridayButton" -> System.out.println("friday");
            case "saturdayButton" -> System.out.println("saturday");
            case "sundayButton" -> System.out.println("sunday");
        }
    }

}
