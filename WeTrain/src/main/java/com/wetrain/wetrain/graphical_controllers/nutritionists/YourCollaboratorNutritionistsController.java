package com.wetrain.wetrain.controllers.nutritionists;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class YourCollaboratorNutritionistsController{
    @FXML
    private Button logoutButton;
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "YourProfileTrainersNutritionists", "", false);
    }
    //TODO implementare metodi
    @FXML
    void sendEmailButtonAction() {

    }
    @FXML
    void changeCollaboratorButtonAction() {

    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
}
