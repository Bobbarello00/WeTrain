package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.ButtonBehavior;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuAthletesController {
    public static ButtonBehavior buttonBehavior = new ButtonBehavior();
    @FXML
    private Button dietButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button staffButton;
    @FXML
    private Button workoutButton;
    @FXML
    void staffButtonAction() throws IOException {
        buttonBehavior.setBehavior(staffButton,"YourPersonalStaff","Athletes");
    }
    @FXML
    void findCourseButtonAction() throws IOException {
        buttonBehavior.setBehavior(findCourseButton,"FindCourse","Athletes");
    }
    @FXML
    void dietButtonAction() throws IOException {
        buttonBehavior.setBehavior(dietButton,"YourDietPlan","Athletes");
    }
    @FXML
    void workoutButtonAction() throws IOException {
        buttonBehavior.setBehavior(workoutButton,"YourWorkoutPlan","Athletes");
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", "Athletes");
    }
}
