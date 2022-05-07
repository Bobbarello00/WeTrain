package viewone.graphical_controllers;

import exception.DBConnectionFailedException;
import exception.invalidDataException.InvalidIbanException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import viewone.engeneering.LoggedUserSingleton;
import viewone.MainPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.bean.UserBean;

import java.sql.SQLException;
import java.util.Objects;

public abstract class ProfileGUIController {
    @FXML protected ImageView usrImage;
    @FXML protected Label emailLabel;
    @FXML protected Label firstNameLabel;
    @FXML protected Label fiscalCodeLabel;
    @FXML protected Label lastNameLabel;
    @FXML protected Button editButton;
    @FXML protected Pane editPane;
    @FXML protected Label paymentMethodLabel;


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

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    protected UserBean getLoggedUser(){
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
        return null;
    }
}