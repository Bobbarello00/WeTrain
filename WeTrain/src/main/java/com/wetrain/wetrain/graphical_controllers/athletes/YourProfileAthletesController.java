package com.wetrain.wetrain.graphical_controllers.athletes;

import com.wetrain.wetrain.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class YourProfileAthletesController {
    @FXML
    private Button editButton;
    @FXML
    private Label emailLabel;
    @FXML
    private Label paymentMethodLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label fiscalCodeLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    protected void closeAction(){
        ((Stage) editButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
    @FXML
    protected void editButtonAction() throws IOException {

    }
}
