package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuTrainersController {
    private Button selectedButton;
    @FXML
    private Button createCourseButton;
    @FXML
    private Button createWorkoutButton;
    @FXML
    private Button manageLessonsButton;
    @FXML
    private Button yourCollaboratorButton;
    @FXML
    void manageLessonsButtonAction() throws IOException {
        buttonBehavior(manageLessonsButton,"ManageLessonsTrainers");
    }
    @FXML
    void createCourseButtonAction() throws IOException {
        buttonBehavior(createCourseButton,"NewCourse");
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        buttonBehavior(createWorkoutButton,"NewWorkoutPlan");
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior(yourCollaboratorButton,"YourCollaboratorTrainers");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome","Trainers");
    }

    void buttonBehavior(Button button, String filename) throws IOException {
        if(selectedButton != button) {
            if (selectedButton != null) {
                selectedButton.setStyle(null);
            }
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24, 147, 21);" +
                    " -fx-border-color: rgb(24, 147, 21);" +
                    " -fx-border-radius: 50;");
            selectedButton = button;
            PageSwitchSimple.switchPage(MainPane.getInstance(), filename, "Trainers");
        } else {
            button.setStyle(null);
            selectedButton = null;
            PageSwitchSimple.switchPage(MainPane.getInstance(), "TrainersHome", "Trainers");
        }
    }
}
