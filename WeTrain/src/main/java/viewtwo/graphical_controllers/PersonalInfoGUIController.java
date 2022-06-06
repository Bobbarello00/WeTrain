package viewtwo.graphical_controllers;

import beans.UserBean;
import controllers.ProfileManagementController;
import engineering.AlertGenerator;
import engineering.LoggedUserSingleton;
import exceptions.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PersonalInfoGUIController implements Initializable {

    @FXML private Label infoBirth;
    @FXML private Label infoEmail;
    @FXML private Label infoFiscalCode;
    @FXML private Label infoName;
    @FXML private Label infoSurname;
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

    public void deleteButtonAction() {
        try {
            if(AlertGenerator.newConfirmationAlert(
                    "ATTENTION!",
                    "CONFIRMATION",
                    "Do you want delete your account? " +
                            "The operation can't be undone.")) {
                new ProfileManagementController().deleteUser();
                PageSwitchSimple.logOff();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
