package com.wetrain.wetrain.Controllers.Athletes;

import com.wetrain.wetrain.MainPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class YourProfileAthletesController {
    @FXML
    private ImageView closeImage;
    @FXML
    private Button editButton;
    @FXML
    private Label emailLabel;
    @FXML
    private Label emailLabel1;
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
    protected void editButtonAction(ActionEvent event) {

    }
}
