package viewone.graphical_controllers.launcher;

import engeneering.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WeTrainGUIController {
    @FXML private Button loginButton;

    @FXML protected void registerTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", "launcher");
    }

    @FXML protected void closeAction(){
        ((Stage) loginButton.getScene().getWindow()).close();
    }

    @FXML protected void loginButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"Login", "launcher");
    }
}
