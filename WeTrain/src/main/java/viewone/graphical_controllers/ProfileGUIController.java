package viewone.graphical_controllers;

import controller.ProfileManagementController;
import exception.DBUnreachableException;
import exception.PersonalizedException;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import viewone.PageSwitchSizeChange;
import viewone.engeneering.AlertFactory;
import viewone.engeneering.LoggedUserSingleton;
import viewone.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class ProfileGUIController {
    @FXML protected ImageView usrImage;
    @FXML protected Label emailLabel;
    @FXML protected Label firstNameLabel;
    @FXML protected Label fiscalCodeLabel;
    @FXML protected Label lastNameLabel;
    @FXML protected Button editButton;
    @FXML protected Pane editPane;
    @FXML protected Label paymentMethodLabel;
    @FXML protected Button deleteButton;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML protected void editAbort(){
        editPane.setDisable(true);
        editPane.setVisible(false);
        editButton.setDisable(false);
        editButton.setVisible(true);
        paymentMethodLabel.setVisible(true);
    }

    @FXML protected void editPaymentMethodButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @FXML protected void deleteButtonAction() {
        try {
            if(AlertFactory.newConfirmationAlert(
                    "ATTENTION!",
                    "CONFIRMATION",
                    "Do you want delete your account? " +
                            "The operation can't be undone.")) {
                profileManagementController.deleteUser();
                PageSwitchSizeChange.logOff();
            }
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertFactory.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    protected void setVisible(Node node, boolean bool) {
        node.setDisable(!bool);
        node.setVisible(bool);
    }

    protected UserBean getLoggedUser(){
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertFactory.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
        return null;
    }
}