package viewone.graphical_controllers.launcher;


import controller.RegistrationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private static String selectedProfile;
    @FXML
    private Button registerButton;
    @FXML
    private RadioButton maleButton;
    @FXML
    private RadioButton femaleButton;
    @FXML
    private RadioButton nogenderButton;
    @FXML
    private DatePicker birthPicker;
    @FXML
    private TextField fcText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField usernameText;
    private char gender;

    private int sendUserInfo(){
        if(!Objects.equals(usernameText.getText(), "")
                & !Objects.equals(firstNameText.getText(), "")
                & !Objects.equals(lastNameText.getText(), "")
                & !Objects.equals(fcText.getText(), "")
                & !Objects.equals(birthPicker.getEditor().getText(), "")){

            UserBean user = new UserBean();
            if(!user.setUsername(usernameText.getText())
                || !user.setName(firstNameText.getText())
                || !user.setSurname(lastNameText.getText())){
                return 3;
            }

            if(!user.setFc(fcText.getText())){
                return 2;
            }
            if(!user.setBirth(birthPicker.getEditor().getText())){
               return 1;
            }
            user.setType(selectedProfile);
            user.setGender(gender);
            try {
                RegistrationController.processUserInfo(user);
            }  catch (SQLIntegrityConstraintViolationException e) {
                //TODO duplicate primary key
                System.out.println("Duplicate primary key");
                return 4;
            } catch (SQLException e){
                System.out.println("Errore nel salvataggio dell'utente.");
                return 5;
            }
            return 0;
        } else {
            return -1;
        }
    }

    @FXML
    private void registerButtonAction() throws IOException {
        int res = sendUserInfo();
        if(res == 0) {
            PageSwitchSizeChange.loadHome(registerButton, selectedProfile + "sHome", selectedProfile + "s");
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OOPS, SOMETHING WENT WRONG!");
            alert.setContentText("Be sure to fill all fields correctly, thanks for your collaboration!");
            //TODO sostituire switch case con try-catch(?)
            switch (res) {
                case (-1) -> alert.setHeaderText("Empty fields");
                case (1) -> alert.setHeaderText("Birth date not valid");
                case (2) -> alert.setHeaderText("Fiscal code not valid");
                case (3) -> {
                    alert.setHeaderText("Excessive length in name, surname or username");
                    alert.setContentText("Be sure that name or surname are under 45 characters and username is under 20 characters, thanks for your collaboration!");
                }
                case (4) -> {
                    alert.setHeaderText("Error in user registration");
                    alert.setContentText("Fiscal code, username or email already existing in our database. \n" +
                            "If you already have an account, log in.");
                }
                case (5) -> {
                    alert.setHeaderText("Error in our database");
                    alert.setContentText("Sorry for the inconvenience");
                }
            }
            alert.showAndWait();
        }
    }

    @FXML
    protected void closeAction(){
        ((Stage) registerButton.getScene().getWindow()).close();
    }
    @FXML
    private void profileButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }
    @FXML
    private void homeButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    private void registrationTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(), selectedProfile + "Registration", HOME);
    }

    public static void setSelectedProfileString(String selectedProfileString){
        selectedProfile = selectedProfileString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
