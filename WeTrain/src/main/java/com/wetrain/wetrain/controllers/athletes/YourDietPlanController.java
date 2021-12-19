package com.wetrain.wetrain.controllers.athletes;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class YourDietPlanController {

    private Button previousButton;
    private Text previousText;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mondayButton;
    @FXML
    private Text mondayText;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Text tuesdayText;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Text wednesdayText;
    @FXML
    private Button thursdayButton;
    @FXML
    private Text thursdayText;
    @FXML
    private Button fridayButton;
    @FXML
    private Text fridayText;
    @FXML
    private Button saturdayButton;
    @FXML
    private Text saturdayText;
    @FXML
    private Button sundayButton;
    @FXML
    private Text sundayText;
    @FXML
    protected void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(editButton, "YourProfileAthletes", "athletes", false);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher",true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
    private void colorShift(Button button, Text text){
        if(previousButton!=null){
            previousButton.setStyle(null);
            previousText.setStyle("-fx-fill: white");
        }
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-color:  rgb(24, 147, 21);" +
                "-fx-border-radius: 50");
        text.setStyle("-fx-fill: rgb(24, 147, 21)");
        previousButton = button;
        previousText = text;
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        colorShift((Button) event.getSource(), ((Text)((Button) event.getSource()).getChildrenUnmodifiable().get(0)));
    }
}
