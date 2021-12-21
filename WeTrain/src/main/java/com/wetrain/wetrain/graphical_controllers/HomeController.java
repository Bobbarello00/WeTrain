package com.wetrain.wetrain.graphical_controllers;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class HomeController {
    @FXML
    abstract void editButtonAction(ActionEvent e) throws IOException;
    @FXML
    void logoutButtonAction(ActionEvent e) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) e.getSource(), "WeTrainGUI", "launcher", true);
    }
    @FXML
    protected void closeAction(MouseEvent e){
        ((Stage) ((ImageView)e.getSource()).getScene().getWindow()).close();
    }
}
