package com.wetrain.wetrain.graphical_controllers.trainers;

import com.wetrain.wetrain.DaysOfTheWeekController;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.graphical_controllers.HomeControllerTrainersNutritionists;
import com.wetrain.wetrain.graphical_controllers.ListPopulate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewWorkoutPlanController extends HomeControllerTrainersNutritionists implements Initializable {
    private static final String HOME = "trainers";
    public final DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    private ListView<Node> exercisesList;
    @FXML
    private ListView<Node> exercisesSelectedList;
    @FXML
    private Button createButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,exercisesList,false);
        ListPopulate.populateList(10,exercisesSelectedList,false);
    }
    @FXML
    public void addExerciseTextAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(createButton, "AddExercise", HOME, false);
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
    @FXML
    void createButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", HOME);
        System.out.println("Created");
    }
}
