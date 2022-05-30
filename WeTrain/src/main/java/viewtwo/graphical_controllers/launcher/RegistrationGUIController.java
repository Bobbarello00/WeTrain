package viewtwo.graphical_controllers.launcher;

import controllers.RegistrationController;
import engeneering.AlertGenerator;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.invalid_data_exception.EmptyFieldsException;
import exceptions.invalid_data_exception.InvalidBirthException;
import exceptions.invalid_data_exception.InvalidDataException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import viewone.beans.CredentialsBean;
import viewone.beans.PersonalInfoBean;
import viewone.beans.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationGUIController implements Initializable {

    @FXML private CheckBox femaleCheck;
    @FXML private Spinner<Integer> yearSelection;
    @FXML private Spinner<Integer> daySelection;
    @FXML private TextField emailTextField;
    @FXML private TextField fiscalCodeTextField;
    @FXML private CheckBox maleCheck;
    @FXML private Spinner<Integer> monthSelection;
    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField usernameTextField;

    private String selectedProfile;
    private final RegistrationController registrationController = new RegistrationController();

    @FXML void backAction() throws IOException {
        PageSwitchSimple.switchPage("ProfileSelection", "launcher");
    }

    @FXML void registerAction() throws IOException {
        UserBean user;
        try {
            LocalDate birth = LocalDate.of(
                    yearSelection.getValue(),
                    monthSelection.getValue(),
                    daySelection.getValue());
            char gender;
            if(maleCheck.isSelected()) {
                gender = 'm';
            } else if(femaleCheck.isSelected()){
                gender = 'f';
            } else {
                throw new EmptyFieldsException();
            }
            user = new UserBean(
                    usernameTextField.getText(),
                    new PersonalInfoBean(
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            birth,
                            fiscalCodeTextField.getText(),
                            gender),
                    selectedProfile,
                    CredentialsBean.ctorWithSyntaxCheck(emailTextField.getText(), passwordTextField.getText())
            );
            registrationController.processUserInfo(user);
            PageSwitchSimple.switchPage(selectedProfile + "sHome", selectedProfile + "s");
        } catch (SQLIntegrityConstraintViolationException e) {
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "Error in user registration",
                    "Fiscal code, username or email already existing in our database. \n" +
                            "If you already have an account, log in.");
        } catch (DateTimeException e) {
            List<String> errorStrings = new InvalidBirthException().getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (DBUnreachableException  e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (UserNotFoundException e) {
            AlertGenerator.newWarningAlert(
                    "OOPS, SOMETHING WENT WRONG!",
                    "Your subscription failed",
                    "Try again."
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        setIntegerValueFactory(daySelection, 1, 31);
        setIntegerValueFactory(monthSelection, 1, 12);
        setIntegerValueFactory(yearSelection, 2000, LocalDate.now().getYear());
        maleCheck.fire();
        maleCheck.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldBool, Boolean newBool) {
                if(newBool) {
                    femaleCheck.setSelected(false);
                }
            }
        });
        femaleCheck.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldBool, Boolean newBool) {
                if(newBool) {
                    maleCheck.setSelected(false);
                }
            }
        });
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            registerAction();
        }
        if(event.getCode() == KeyCode.ESCAPE) {
            backAction();
        }
    }

    private void setIntegerValueFactory(Spinner<Integer> spinner, int start, int end) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, end);
        valueFactory.setValue(start);
        spinner.setValueFactory(valueFactory);
    }

    public void setSelectedProfile(String selectedProfile) {
        this.selectedProfile = selectedProfile;
    }
}
