package viewone.graphical_controllers.launcher;

import controller.RegistrationController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import viewone.AlertFactory;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import viewone.bean.CredentialsBean;

import java.io.IOException;
import java.util.Objects;

public class RegistrationGUIController extends LauncherGUIController implements Initializable {
    private static final String HOME = "launcher";

    @FXML private TextField emailField;
    @FXML private TextField passwField;

    @FXML private void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }

    @FXML private void continueButtonAction() throws IOException {
        if(!Objects.equals(emailField.getText(), "") & !Objects.equals(passwField.getText(), "") & sendCredentialInfo()) {
            PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME);
        } else {
            AlertFactory.newWarningAlert("WARNING!",
                    "Email or password not inserted or mistyped.",
                    "Be sure to fill all fields correctly, thanks.\n " +
                            "Remember that the password must contain between eight and 45 characters," +
                            " at least one number and both lower and uppercase letters and " +
                            "special characters (e.g. @!#$%^&+=) and must not contain tabs or spaces");
        }
    }

    @FXML private void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    private boolean sendCredentialInfo() {
        CredentialsBean credential = new CredentialsBean();
        if(!credential.setEmail(emailField.getText())
                || !credential.setPassword(passwField.getText())){
            return false;
        }
        RegistrationController.setCredentialInfo(credential);
        return true;
    }
}
