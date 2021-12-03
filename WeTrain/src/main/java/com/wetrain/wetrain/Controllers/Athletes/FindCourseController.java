package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FindCourseController implements Initializable {

    private Boolean[] selected = new Boolean[7];
    @FXML
    private TextField courseNameText;
    @FXML
    private Button dietButton;
    @FXML
    private Button editButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button fridayButton;
    @FXML
    private Button logoutButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button mondayButton;
    @FXML
    private ListView<?> resultList;
    @FXML
    private Button saturdayButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button staffButton;
    @FXML
    private Button sundayButton;
    @FXML
    private Button thursdayButton;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Button workoutButton;
    @FXML
    void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> selectedDayButtonAction(mondayButton,0);
            case "tuesdayButton" -> selectedDayButtonAction(tuesdayButton,1);
            case "wednesdayButton" -> selectedDayButtonAction(wednesdayButton,2);
            case "thursdayButton" -> selectedDayButtonAction(thursdayButton,3);
            case "fridayButton" -> selectedDayButtonAction(fridayButton,4);
            case "saturdayButton" -> selectedDayButtonAction(saturdayButton,5);
            case "sundayButton" -> selectedDayButtonAction(sundayButton,6);
        }
    }
    private void selectedDayButtonAction(Button button, int i){
        if(!selected[i]) {
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24,147,21);" +
                    " -fx-border-color: rgb(24,147,21);" +
                    " -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }
        selected[i]=!selected[i];
    }
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourDietPlan", "Athletes");
        mainPane.setCenter(view);
    }
    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void findCourseButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthletesHome", "Athletes");
        mainPane.setCenter(view);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthletesHome", "Athletes");
        mainPane.setCenter(view);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);
    }
    @FXML
    void searchButtonAction(ActionEvent event) {
        System.out.println("Search done");
    }
    @FXML
    void staffButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourPersonalStaff", "Athletes");
        mainPane.setCenter(view);
    }
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("YourWorkoutPlan", "Athletes");
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(selected, Boolean.FALSE);
    }
}