package viewone.graphical_controllers.launcher;

import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;

public class RegistrationController extends LauncherController implements Initializable {
    private static final String HOME = "launcher";
    @FXML
    protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"MoreInfo", HOME);
    }
    @FXML
    protected void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }
}
