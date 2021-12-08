package com.wetrain.wetrain.Controllers.Trainers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddExerciseController {

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea equipmentTextArea;

    @FXML
    private BorderPane secondaryPane;

    @FXML
    private TextField nameText;


    @FXML
    void addButtonAction(ActionEvent event) {
        ((Stage)addButton.getScene().getWindow()).close();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        ((Stage)addButton.getScene().getWindow()).close();
    }
}