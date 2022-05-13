package viewone.graphical_controllers.launcher;


import controller.RegistrationController;
import exception.*;
import exception.invalid_data_exception.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import viewone.bean.CredentialsBean;
import viewone.bean.PersonalInfoBean;
import viewone.engeneering.AlertFactory;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.UserBean;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class MoreInfoGUIController implements Initializable {
    private static final String HOME = "launcher";
    private char gender;
    private String email;
    private String password;
    private String selectedProfile;

    @FXML private Button registerButton;
    @FXML private RadioButton maleButton;
    @FXML private RadioButton femaleButton;
    @FXML private DatePicker birthPicker;
    @FXML private TextField fcText;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField usernameText;

    private final RegistrationController registrationController = new RegistrationController();

    private void sendUserInfo() throws SQLException, InvalidUserInfoException, InvalidFiscalCodeException, InvalidBirthException, EmptyFieldsException, InvalidCredentialsException, DBUnreachableException {
        UserBean user = new UserBean(
                usernameText.getText(),
                new PersonalInfoBean(
                        firstNameText.getText(),
                        lastNameText.getText(),
                        birthPicker.getEditor().getText(),
                        fcText.getText(),
                        gender),
                selectedProfile,
                CredentialsBean.ctorWithSyntaxCheck(email, password)
        );
        registrationController.processUserInfo(user);
    }

    @FXML private void registerButtonAction() throws IOException {
        try{
            sendUserInfo();
            PageSwitchSizeChange.loadHome(registerButton, selectedProfile + "sHome", selectedProfile + "s");
        } catch (SQLIntegrityConstraintViolationException e) {
            AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "Error in user registration",
                    "Fiscal code, username or email already existing in our database. \n" +
                            "If you already have an account, log in.");
        } catch (SQLException e){
            //TODO gestirla meglio
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.alert();
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
        }
    }

    @FXML protected void closeAction(){
        ((Stage) registerButton.getScene().getWindow()).close();
    }

    @FXML private void profileButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    @FXML private void homeButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }

    @FXML private void registrationTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(), selectedProfile + "Registration", HOME);
    }

    public void setSelectedProfileString(String selectedProfileString){
        this.selectedProfile = selectedProfileString;
    }

    public void setCredentialInfo(CredentialsBean bean) {
        this.email = bean.getEmail();
        this.password = bean.getPassword();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
        group.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if(newToggle == maleButton) {
                        gender = 'm';
                    } else if (newToggle == femaleButton){
                        gender = 'f';
                    }
                }
        );
        group.selectToggle(maleButton);
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            registerButtonAction();
        }
        if(event.getCode() == KeyCode.ESCAPE) {
            registrationTextAction();
        }
        if(event.getCode() == KeyCode.F1){
            if(firstNameText.isFocused()) {
                lastNameText.requestFocus();
            }else if(lastNameText.isFocused()) {
                fcText.requestFocus();
            }else if(fcText.isFocused()) {
                usernameText.requestFocus();
            }else if(usernameText.isFocused()) {
                birthPicker.requestFocus();
            }else if(birthPicker.isFocused()) {
                maleButton.requestFocus();
            }else if(maleButton.isFocused()) {
                femaleButton.requestFocus();
            }else {
                firstNameText.requestFocus();
            }
        }
    }
}
