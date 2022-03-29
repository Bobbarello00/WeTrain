package viewone.graphical_controllers.launcher;

import controller.RegistrationController;
import javafx.scene.control.TextField;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import viewone.bean.CredentialBean;

import java.io.IOException;
import java.util.Objects;

public class RegistrationGUIController extends LauncherGUIController implements Initializable {
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
            sendCredentialInfo();
            PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME);
        } else {
            System.out.println("Inserire email e password.");
        }
    }
    @FXML
    protected void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    public static void sendCredentialInfo() {
        CredentialBean credential = new CredentialBean(emailField.getText(), passwField.getText());
        RegistrationController.setCredentialInfo(credential);
    }
}
