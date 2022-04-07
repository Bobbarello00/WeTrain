package viewone.graphical_controllers.trainers;

import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.graphical_controllers.FitnessLevelFilterGUIController;
import viewone.graphical_controllers.TimeSchedulerGUIController;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NewCourseGUIController extends HomeGUIControllerTrainers implements Initializable {
    public Boolean[] toggled = new Boolean[7];
    private final FitnessLevelFilterGUIController fitnessLevelFilter= new FitnessLevelFilterGUIController();

    @FXML private ListView<Node> exercisesSelectedList;
    @FXML private Button mondayButton;
    @FXML private Button createButton;
    @FXML private TimeSchedulerGUIController mondayTimeSchedulerController;
    @FXML private Parent mondayTimeScheduler;
    @FXML private Button tuesdayButton;
    @FXML private TimeSchedulerGUIController tuesdayTimeSchedulerController;
    @FXML private Parent tuesdayTimeScheduler;
    @FXML private Button wednesdayButton;
    @FXML private TimeSchedulerGUIController wednesdayTimeSchedulerController;
    @FXML private Parent wednesdayTimeScheduler;
    @FXML private Button thursdayButton;
    @FXML private TimeSchedulerGUIController thursdayTimeSchedulerController;
    @FXML private Parent thursdayTimeScheduler;
    @FXML private Button fridayButton;
    @FXML private TimeSchedulerGUIController fridayTimeSchedulerController;
    @FXML private Parent fridayTimeScheduler;
    @FXML private Button saturdayButton;
    @FXML private TimeSchedulerGUIController saturdayTimeSchedulerController;
    @FXML private Parent saturdayTimeScheduler;
    @FXML private Button sundayButton;
    @FXML private TimeSchedulerGUIController sundayTimeSchedulerController;
    @FXML private Parent sundayTimeScheduler;
    @FXML private TextField courseNameText;
    @FXML private TextArea infoTextArea;
    @FXML private TextArea equipmentTextArea;
    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private Text usernameText1;

    public NewCourseGUIController() {
    }

    @FXML void createButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "trainers");
        MenuTrainersGUIController.resetSelectedButton();
        System.out.println("Created");
    }
    @FXML void fitnessLevelSelection(ActionEvent event){
        fitnessLevelFilter.fitnessLevelSelection(event);
    }

    @FXML void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> toggledDayButtonAction(mondayTimeSchedulerController,mondayButton,0);
            case "tuesdayButton" -> toggledDayButtonAction(tuesdayTimeSchedulerController,tuesdayButton,1);
            case "wednesdayButton" -> toggledDayButtonAction(wednesdayTimeSchedulerController,wednesdayButton,2);
            case "thursdayButton" -> toggledDayButtonAction(thursdayTimeSchedulerController,thursdayButton,3);
            case "fridayButton" -> toggledDayButtonAction(fridayTimeSchedulerController,fridayButton,4);
            case "saturdayButton" -> toggledDayButtonAction(saturdayTimeSchedulerController,saturdayButton,5);
            case "sundayButton" -> toggledDayButtonAction(sundayTimeSchedulerController,sundayButton,6);
        }
    }

    private void toggledDayButtonAction(TimeSchedulerGUIController controller, Button button, int i){
        toggled[i]=!toggled[i];
        controller.toggleVisibility(toggled[i]);
        if(toggled[i]) {
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24,147,21);" +
                    " -fx-border-color: rgb(24,147,21);" +
                    " -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        baseFitnessLevelButton.fire();
        Arrays.fill(toggled, Boolean.FALSE);
        setUsername();
    }
}