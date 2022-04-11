package viewone.graphical_controllers.athletes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import viewone.graphical_controllers.FitnessLevelFilterGUIController;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FindCourseGUIController extends HomeGUIControllerAthletes implements Initializable {

    private final Boolean[] selected = new Boolean[7];
    private final FitnessLevelFilterGUIController fitnessLevelFilter= new FitnessLevelFilterGUIController();

    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private TextField courseNameText;
    @FXML private Button fridayButton;
    @FXML private Button mondayButton;
    @FXML private ListView<?> resultList;
    @FXML private Button saturdayButton;
    @FXML private Button searchButton;
    @FXML private Button sundayButton;
    @FXML private Button thursdayButton;
    @FXML private Button tuesdayButton;
    @FXML private Button wednesdayButton;
    @FXML private Text usernameText;

    @FXML private void dayButtonAction(ActionEvent event) {
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

    @FXML void fitnessLevelSelection(ActionEvent event){
        fitnessLevelFilter.fitnessLevelSelection(event);
    }

    @FXML protected void searchButtonAction() {
        System.out.println("Search done, showing results...");
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        baseFitnessLevelButton.fire();
        Arrays.fill(selected, Boolean.FALSE);
        setUsername();
    }
}