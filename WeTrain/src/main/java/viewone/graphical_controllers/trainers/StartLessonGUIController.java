package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewone.engeneering.AlertFactory;
import viewone.graphical_controllers.AbstractFormGUIController;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class StartLessonGUIController extends AbstractFormGUIController {
    @FXML private TextField urlTextField;

    @Override protected void sendAction() {
        Desktop desktop = Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(new URI(urlTextField.getText()));
            } catch (IOException | URISyntaxException e) {
                AlertFactory.newWarningAlert("EXCEPTION!",
                        "Url not working",
                        "The url inserted by the trainer is incorrect or not working anymore.");
            }
        }else{
            AlertFactory.newWarningAlert("BROWSING ERROR",
                    "Browsing not supported",
                    "Sorry for the inconvenience but you can't access this web resource");
        }
    }
}