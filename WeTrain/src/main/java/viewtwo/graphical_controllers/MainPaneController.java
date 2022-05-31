package viewtwo.graphical_controllers;

import engeneering.MainPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {

    @FXML private BorderPane mainPane;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPane.setInstance(mainPane);
    }
}
