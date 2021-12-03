package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.DaysOfTheWeekController;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourWorkoutPlanController implements Initializable {

    private DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    public Button mondayButton;
    @FXML
    public Button tuesdayButton;
    @FXML
    public Button wednesdayButton;
    @FXML
    public Button thursdayButton;
    @FXML
    public Button fridayButton;
    @FXML
    public Button saturdayButton;
    @FXML
    public Button sundayButton;
    @FXML
    private Button staffButton;
    @FXML
    private Button findCourseButton;
    @FXML
    private Button dietButton;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private ListView exercisesList;
    @FXML
    private Button workoutButton;
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"AthletesHome", "Athletes");
    }
    @FXML
    void staffButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"YourPersonalStaff", "Athletes");
    }
    @FXML
    void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"FindCourse", "Athletes");
    }
    @FXML
    void dietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"YourDietPlan", "Athletes");
    }
    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);}
    @FXML
    void workoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"AthletesHome", "Athletes");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,exercisesList,false);
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
}
