package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewWorkoutPlanController implements Initializable {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private ListView exercisesList;
    @FXML
    private ListView exercisesSelectedList;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLessonsButt;
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
        Pane view = loader.getPage("manageLessonsTrainers", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void createCourseButtonAction() {System.out.println("Create New Course");}
    @FXML
    void createWorkoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("manageRequestsTrainers", "Trainers");
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
        ListPopulate.populateList(10,exercisesList,false);
        ListPopulate.populateList(10,exercisesSelectedList,false);
    }

    @FXML
    public void addExerciseTextAction() {
        System.out.println("ExerciseAdded");
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



    //TODO Search bar ----------------------------------------------------

}
