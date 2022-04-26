package viewone.graphical_controllers;

import exception.DBConnectionFailedException;
import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
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
    @FXML protected Label emailLabel;
    @FXML protected Label firstNameLabel;
    @FXML protected Label fiscalCodeLabel;
    @FXML protected Label lastNameLabel;

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    protected UserBean getLoggedUser(){
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (ExpiredCardException | InvalidCardInfoException e){
            e.alert();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionFailedException e) {
            e.alert();
        }
        return null;
    }
}