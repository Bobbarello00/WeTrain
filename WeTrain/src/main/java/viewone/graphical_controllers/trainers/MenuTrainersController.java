package viewone.graphical_controllers.trainers;

import viewone.ButtonBehavior;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuTrainersController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "trainers";
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
        buttonBehavior.setBehavior(manageLessonsButton,"ManageLessonsTrainers",HOME);
    }
    @FXML
    void createCourseButtonAction() throws IOException {
        buttonBehavior.setBehavior(createCourseButton,"NewCourse",HOME);
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        buttonBehavior.setBehavior(createWorkoutButton,"NewWorkoutPlan",HOME);
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior.setBehavior(yourCollaboratorButton,"YourCollaboratorTrainers",HOME);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome",HOME);
        buttonBehavior.resetSelectedButton();
    }

    public static void resetSelectedButton(){
        buttonBehavior.resetSelectedButton();
    }

}
