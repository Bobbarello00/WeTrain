package viewone.graphical_controllers.launcher;


import controller.LoginController;
import exception.ElementNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Athlete;
import model.LoggedUserSingleton;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.CredentialsBean;

import java.io.IOException;
import java.sql.SQLException;

public class LoginGUIController extends LauncherGUIController {
    @FXML
    private Button submitButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwField;
    @FXML
    private TextField passwSField;
    @FXML
    void homeAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", "launcher");
    }
    @FXML
    void submitButtonAction() {
        try {
            LoginController.login(new CredentialsBean(emailField.getText(), passwField.getText()));
            if(LoggedUserSingleton.getInstance() instanceof Athlete){
                PageSwitchSizeChange.loadHome(submitButton, "AthletesHome", "athletes");
            } else {
                PageSwitchSizeChange.loadHome(submitButton, "TrainersHome", "trainers");
            }
        } catch (ElementNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OOPS, SOMETHING WENT WRONG!");
            alert.setHeaderText("User not found.");
            alert.setContentText("Be sure that you have an account on WeTrain.");
            alert.showAndWait();
        } catch (SQLException e) {
            //TODO Exception
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void keyHandler(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            submitButtonAction();
        }
        if(event.getCode() == KeyCode.ESCAPE){
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