package viewone.graphical_controllers.launcher;


import controllers.LoginController;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import engineering.LoggedUserSingleton;
import engineering.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import beans.AthleteBean;
import beans.CredentialsBean;
import engineering.AlertGenerator;
import beans.UserBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginGUIController extends LauncherGUIController{
    @FXML private Button submitButton;
    @FXML private TextField emailField;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;

    private final LoginController loginController = new LoginController();

    @FXML void homeAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", "launcher");
    }

    @FXML void submitButtonAction() {
        try {
            LoggedUserSingleton.resetUserInfo();
            UserBean user = loginController.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(), passwField.getText()));
            LoggedUserSingleton.setFc(user.getFiscalCode());
            if(getLoggedUser() instanceof AthleteBean){
                PageSwitchSizeChange.loadHome(submitButton, "AthletesHome", "athletes");
            } else {
                PageSwitchSizeChange.loadHome(submitButton, "TrainersHome", "trainers");
            }
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
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            submitButtonAction();
        }
        if(event.getCode() == KeyCode.ESCAPE) {
            homeAction();
        }
        if(event.getCode() == KeyCode.F1){
            if(emailField.isFocused()){
                if(passwField.isVisible()){
                    passwField.requestFocus();
                }else{
                    passwSField.requestFocus();
                }
            } else {
                emailField.requestFocus();
            }
        }
    }

}