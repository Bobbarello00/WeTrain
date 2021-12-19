package com.wetrain.wetrain.controllers.athletes;

import com.wetrain.wetrain.ButtonBehavior;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuAthletesController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "athletes";
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
        buttonBehavior.setBehavior(staffButton,"YourPersonalStaff",HOME);
    }
    @FXML
    void findCourseButtonAction() throws IOException {
        buttonBehavior.setBehavior(findCourseButton,"FindCourse",HOME);
    }
    @FXML
    void dietButtonAction() throws IOException {
        buttonBehavior.setBehavior(dietButton,"YourDietPlan",HOME);
    }
    @FXML
    void workoutButtonAction() throws IOException {
        buttonBehavior.setBehavior(workoutButton,"YourWorkoutPlan",HOME);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", HOME);
        buttonBehavior.resetSelectedButton();
    }
}
