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
    protected void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        if(!Objects.equals(emailField.getText(), "") & !Objects.equals(passwField.getText(), "")) {
            sendCredentialInfo();
            PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ATTENZIONE!");
            alert.setHeaderText("Inserire email e password.");
            alert.setContentText("Assicurati di aver compilato tutti i campi prima di procedere, grazie.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    public void sendCredentialInfo() {
        CredentialBean credential = new CredentialBean(emailField.getText(), passwField.getText());
        RegistrationController.setCredentialInfo(credential);
    }
}
