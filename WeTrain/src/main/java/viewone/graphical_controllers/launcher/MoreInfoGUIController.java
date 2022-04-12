package viewone.graphical_controllers.launcher;


import controller.RegistrationController;
import exception.EmptyFieldsException;
import exception.InvalidBirthException;
import exception.InvalidFiscalCodeException;
import exception.InvalidUserInfoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import viewone.AlertFactory;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.UserBean;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MoreInfoGUIController implements Initializable {
    private static final String HOME = "launcher";
    private char gender;

    private static String selectedProfile;
    @FXML private Button registerButton;
    @FXML private RadioButton maleButton;
    @FXML private RadioButton femaleButton;
    @FXML private RadioButton nogenderButton;
    @FXML private DatePicker birthPicker;
    @FXML private TextField fcText;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField usernameText;

    private final RegistrationController registrationController = RegistrationController.getInstance();

    private void sendUserInfo() throws SQLException, InvalidUserInfoException, InvalidFiscalCodeException, InvalidBirthException, EmptyFieldsException {
        if(!Objects.equals(usernameText.getText(), "")
                & !Objects.equals(firstNameText.getText(), "")
                & !Objects.equals(lastNameText.getText(), "")
                & !Objects.equals(fcText.getText(), "")
                & !Objects.equals(birthPicker.getEditor().getText(), "")){

            UserBean user = new UserBean();

            if(!user.setUsername(usernameText.getText())
                || !user.setName(firstNameText.getText())
                || !user.setSurname(lastNameText.getText())){
                throw new InvalidUserInfoException();
            }
            if(!user.setFc(fcText.getText())){
                throw new InvalidFiscalCodeException();
            }
            if(!user.setBirth(birthPicker.getEditor().getText())){
               throw new InvalidBirthException();
            }
            user.setType(selectedProfile);
            user.setGender(gender);
            registrationController.processUserInfo(user);
        } else {
            throw new EmptyFieldsException();
        }
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
            AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "Error in our database",
                    "Sorry for the inconvenience.");
        } catch (InvalidFiscalCodeException | InvalidUserInfoException | InvalidBirthException | EmptyFieldsException e) {
            e.alert();
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

    public static void setSelectedProfileString(String selectedProfileString){
        selectedProfile = selectedProfileString;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        nogenderButton.setToggleGroup(group);
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
        group.selectToggle(nogenderButton);
        group.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if(newToggle == maleButton) {
                        gender = 'm';
                    } else if(newToggle == femaleButton) {
                        gender = 'f';
                    } else {
                        gender = 'x';
                    }
                }
        );
    }
}
