package viewone.graphical_controllers;

import viewone.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class ProfileGUIController {
    @FXML private Label emailLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label fiscalCodeLabel;
    @FXML private Label lastNameLabel;

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}