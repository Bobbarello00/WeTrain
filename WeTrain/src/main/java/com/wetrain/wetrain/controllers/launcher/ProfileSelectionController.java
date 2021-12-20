package com.wetrain.wetrain.controllers.launcher;


import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileSelectionController {
    private static final String HOME = "launcher";
    @FXML
    private Button athletesButton;
    @FXML
    protected void closeAction(){
        ((Stage) athletesButton.getScene().getWindow()).close();
    }
    @FXML
    protected void athletesButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthleteRegistration", HOME);
        MoreInfoController.setSelectedProfileString("Athlete");
    }
    @FXML
    protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    protected void nutritionistButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistRegistration", HOME);
        MoreInfoController.setSelectedProfileString("Nutritionist");
    }
    @FXML
    protected void trainersButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainerRegistration", HOME);
        MoreInfoController.setSelectedProfileString("Trainer");
    }
    @FXML
    void buttonColorShiftEntered(MouseEvent event){
        ((Button) event.getSource()).setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    void buttonColorShiftExited(MouseEvent event){
        ((Button) event.getSource()).setStyle("-fx-background-color:  rgba(24, 147, 21, 0.65); -fx-background-radius: 25");
    }

}
