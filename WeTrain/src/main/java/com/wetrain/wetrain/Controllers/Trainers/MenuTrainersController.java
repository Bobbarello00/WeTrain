package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.ButtonBehavior;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuTrainersController {
    public static ButtonBehavior buttonBehavior = new ButtonBehavior();
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
        buttonBehavior.setBehavior(manageLessonsButton,"ManageLessonsTrainers","Trainers");
    }
    @FXML
    void createCourseButtonAction() throws IOException {
        buttonBehavior.setBehavior(createCourseButton,"NewCourse","Trainers");
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        buttonBehavior.setBehavior(createWorkoutButton,"NewWorkoutPlan","Trainers");
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior.setBehavior(yourCollaboratorButton,"YourCollaboratorTrainers","Trainers");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome","Trainers");
    }
}
