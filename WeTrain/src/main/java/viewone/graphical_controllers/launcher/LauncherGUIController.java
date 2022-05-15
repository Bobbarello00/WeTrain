package viewone.graphical_controllers.launcher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.PasswordBehaviorActivation;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class LauncherGUIController implements Initializable {
    @FXML private TextField passwSField;
    @FXML private PasswordField passwField;
    @FXML private CheckBox checkVisible;
    @FXML void eyeButtonAction() {
        checkVisible.fire();
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
    }

    protected UserBean getLoggedUser(){
        return Objects.requireNonNull(LoggedUserSingleton.getInstance());
    }
}
