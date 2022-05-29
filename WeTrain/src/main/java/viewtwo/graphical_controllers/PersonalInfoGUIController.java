package viewtwo.graphical_controllers;

import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import viewone.bean.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PersonalInfoGUIController implements Initializable {

    @FXML private Label infoBirth;
    @FXML private Label infoEmail;
    @FXML private Label infoFiscalCode;
    @FXML private Label infoName;
    @FXML private Label infoSurname;
    @FXML private VBox infoBox;
    @FXML private Label infoUsername;

    private String filename;
    private String path;

    public void setBackPath(String filename, String pathString) {
        this.filename = filename;
        path = pathString;
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage(filename, path);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserBean user = LoggedUserSingleton.getInstance();
            infoBirth.setText(user.getBirth().toString());
            infoEmail.setText(user.getEmail());
            infoName.setText(user.getName());
            infoFiscalCode.setText(user.getFiscalCode());
            infoSurname.setText(user.getSurname());
            infoUsername.setText(user.getUsername());
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }

    }
}
