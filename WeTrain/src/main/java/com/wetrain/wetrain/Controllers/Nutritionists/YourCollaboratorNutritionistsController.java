package com.wetrain.wetrain.controllers.nutritionists;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class YourCollaboratorNutritionistsController{
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button certificationsButton;
    @FXML
    private Button sendEmailButton;
    @FXML
    private Button changeCollaboratorButton;
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "YourProfileTrainersNutritionists", "", false);
    }
    //TODO implementare metodi
    @FXML
    void certificationsButtonAction() {

    }
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
