package com.wetrain.wetrain.controllers.athletes;

import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.controllers.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AthletesHomeController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private ListView<Node> coursesList;
    @FXML
    private ListView<Node> popularsList;
    @FXML
    private Button editButton;
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(editButton, "YourProfileAthletes", "athletes", false);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }

    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList,true);
        ListPopulate.populateList(10,popularsList,false);
    }
}
