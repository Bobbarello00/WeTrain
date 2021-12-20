package com.wetrain.wetrain.controllers.launcher;


import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {
    private static final String HOME = "launcher";
    private static String string;
    @FXML
    private Button registerButton;
    @FXML
    private RadioButton maleButton;
    @FXML
    private RadioButton femaleButton;
    @FXML
    private RadioButton nogenderButton;
    @FXML
    void registerButtonAction() throws IOException {
        PageSwitchSizeChange.loadHome(registerButton, string + "sHome", string + "s");
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
        PageSwitchSimple.switchPage(MainPane.getInstance(),string + "Registration", HOME);
    }

    public static void setSelectedProfileString(String selectedProfileString){
        string = selectedProfileString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        nogenderButton.setToggleGroup(group);
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
    }
}
