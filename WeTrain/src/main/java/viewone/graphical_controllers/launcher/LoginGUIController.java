package viewone.graphical_controllers.launcher;


import controller.LoginController;
import exception.ElementNotFoundException;
import exception.InvalidCredentialsException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import viewone.engeneering.AlertFactory;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.AthleteBean;
import viewone.bean.CredentialsBean;

import java.io.IOException;
import java.sql.SQLException;

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
            loginController.login(new CredentialsBean(emailField.getText(), passwField.getText()));
            if(getLoggedUser() instanceof AthleteBean){
                PageSwitchSizeChange.loadHome(submitButton, "AthletesHome", "athletes");
            } else {
                PageSwitchSizeChange.loadHome(submitButton, "TrainersHome", "trainers");
            }
        } catch (ElementNotFoundException e){
            AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "User not found.",
                    "Be sure that you have an account on WeTrain.");
        } catch (SQLException e) {
            //TODO Exception
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCredentialsException e) {
            e.alert();
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
            }else {
                emailField.requestFocus();
            }
        }
    }

}