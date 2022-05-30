package viewtwo.graphical_controllers.launcher;

import controllers.LoginController;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import viewone.beans.AthleteBean;
import viewone.beans.CredentialsBean;
import viewone.beans.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginGUIController {

    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;

    private final LoginController loginController = new LoginController();

    @FXML void backAction() throws IOException {
        PageSwitchSimple.switchPage("WeTrainGUI", "launcher");
    }

    @FXML void loginAction() {
        try {
            LoggedUserSingleton.resetUserInfo();
            UserBean user = loginController.login(CredentialsBean.ctorWithSyntaxCheck(emailTextField.getText(), passwordField.getText()));
            LoggedUserSingleton.setFc(user.getFiscalCode());
            if(LoggedUserSingleton.getInstance() instanceof AthleteBean){
                PageSwitchSimple.switchPage("AthletesHome", "athletes");
            } else {
                PageSwitchSimple.switchPage("TrainersHome", "trainers");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException | InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (UserNotFoundException e) {
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "User not found.",
                    "Be sure that you have an account on WeTrain.");
        }
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            loginAction();
        }
        if(event.getCode() == KeyCode.ESCAPE) {
            backAction();
        }
    }

}
