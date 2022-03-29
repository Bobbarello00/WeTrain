package viewone.graphical_controllers.launcher;

import javafx.scene.control.TextField;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController extends LauncherController implements Initializable {
    private static final String HOME = "launcher";
    @FXML
    private static TextField emailField;
    @FXML
    private static TextField passwField;
    @FXML
    protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        if(!Objects.equals(emailField.getText(), "") & !Objects.equals(passwField.getText(), "")) {
            //TODO
            NewUser.getInstance().setEmail(emailField.getText());
            NewUser.getInstance().setPassword(passwField.getText());
            PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME);
        } else {
            System.out.println("Inserire email e password.");
        }
    }
    @FXML
    protected void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }
}
