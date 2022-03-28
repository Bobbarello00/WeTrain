package viewone.graphical_controllers.launcher;


import javafx.scene.control.*;
import viewone.bean.TrainerBean;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {
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
    @FXML
    void registerButtonAction() throws IOException {
        //TODO
        if(!Objects.equals(usernameText.getText(), "") & !Objects.equals(firstNameText.getText(), "") & !Objects.equals(lastNameText.getText(), "") & !Objects.equals(fcText.getText(), "") & birthPicker.getValue() != null) {
            NewUser.getInstance().setUsername(usernameText.getText());
            NewUser.getInstance().setName(firstNameText.getText());
            NewUser.getInstance().setSurname(lastNameText.getText());
            NewUser.getInstance().setFc(fcText.getText());
            NewUser.getInstance().setBirth(birthPicker.getValue());
            if (Objects.equals(selectedProfile, "Athlete")) {

            } else {
                NewUser.setInstance(new TrainerBean());
            }
            PageSwitchSizeChange.loadHome(registerButton, selectedProfile + "sHome", selectedProfile + "s");
        }
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
        ToggleGroup group = new ToggleGroup();
        nogenderButton.setToggleGroup(group);
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
    }
}
