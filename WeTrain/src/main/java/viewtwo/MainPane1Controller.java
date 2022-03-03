package viewtwo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import viewone.MainPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPane1Controller implements Initializable {
    @FXML
    private BorderPane mainPane1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPane.setInstance(mainPane1);
    }
}
