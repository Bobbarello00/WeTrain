package viewone.graphical_controllers;

import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class HomeController {
    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;

    @FXML void logoutButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
