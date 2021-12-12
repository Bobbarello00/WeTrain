package com.wetrain.wetrain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyProfileTrainersNutritionistsController {
    @FXML
    private Button editButton;
    @FXML
    private Label emailLabel;
    @FXML
    private Label ibanLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label fiscalCodeLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    protected void closeAction(){
        ((Stage) editButton.getScene().getWindow()).close();
    }
    @FXML
    void editButtonAction(ActionEvent event) {

    }
}
