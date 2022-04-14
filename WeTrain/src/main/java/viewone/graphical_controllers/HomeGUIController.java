package viewone.graphical_controllers;

import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
import javafx.scene.text.Text;
import viewone.engeneering.LoggedUserSingleton;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.bean.UserBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public abstract class HomeGUIController {
    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;
    @FXML protected Text usernameText1;

    protected void setUsername() {
        usernameText1.setText(getLoggedUser().getUsername());
    }

    protected UserBean getLoggedUser(){
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (ExpiredCardException | InvalidCardInfoException e){
            e.alert();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML void logoutButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
