package viewone.graphical_controllers.launcher;


import javafx.scene.control.*;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import viewone.bean.UserBean;

import java.io.IOException;
import java.net.URL;
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
    ToggleGroup group;
    char gender;

    public UserBean getValue(){
        if(!Objects.equals(usernameText.getText(), "") & !Objects.equals(firstNameText.getText(), "") & !Objects.equals(lastNameText.getText(), "") & !Objects.equals(fcText.getText(), "") & birthPicker.getValue() != null) {
            UserBean user = new UserBean();

            user.setUsername(usernameText.getText());
            user.setName(firstNameText.getText());
            user.setSurname(lastNameText.getText());
            user.setFc(fcText.getText());
            user.setBirth(birthPicker.getValue());
            user.setType(selectedProfile);
            user.setGender(gender);
            return user;
        } else {
            //Notifica all'utente che deve inserire tutti i campi
        }
        return null;
    }

    @FXML
    public void registerButtonAction() throws IOException {
        PageSwitchSizeChange.loadHome(registerButton, selectedProfile + "sHome", selectedProfile + "s");
    }
    @FXML
    protected void closeAction(){
        ((Stage) registerButton.getScene().getWindow()).close();
    }
    @FXML
    void profileButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }
    @FXML
    private void homeButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }
    @FXML
    void registrationTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(), selectedProfile + "Registration", HOME);
    }

    public static void setSelectedProfileString(String selectedProfileString){
        selectedProfile = selectedProfileString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group = new ToggleGroup();
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
