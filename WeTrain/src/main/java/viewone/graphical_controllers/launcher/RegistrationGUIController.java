package viewone.graphical_controllers.launcher;

import controller.RegistrationController;
import javafx.scene.control.Alert;
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
    private TextField emailField;
    @FXML
    private TextField passwField;
    @FXML
    private void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    private void continueButtonAction() throws IOException {
        if(!Objects.equals(emailField.getText(), "") & !Objects.equals(passwField.getText(), "") & sendCredentialInfo()) {
            PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Email or password not inserted or mistyped.");
            alert.setContentText("Be sure to fill all fields correctly, thanks.\n " +
                    "Remember that the password must contain between eight and 45 characters," +
                    " at least one number and both lower and uppercase letters and " +
                    "special characters (e.g. @!#$%^&+=) and must not contain tabs or spaces");
            alert.showAndWait();
        }
    }
    @FXML
    private void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    private boolean sendCredentialInfo() {
        CredentialBean credential = new CredentialBean();
        if(!credential.setEmail(emailField.getText())
                || !credential.setPassword(passwField.getText())){
            return false;
        }
        RegistrationController.setCredentialInfo(credential);
        return true;
    }
}
