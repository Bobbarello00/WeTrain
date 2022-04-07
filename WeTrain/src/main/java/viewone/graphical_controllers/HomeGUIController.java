package viewone.graphical_controllers;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public abstract class HomeGUIController {
    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;
    @FXML protected Text usernameText1;

    protected void setUsername() {
        try {
            usernameText1.setText(LoggedUserSingleton.getInstance().getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML void logoutButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
