package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {
    public static String string;
    @FXML
    private Button registerButton;
    @FXML
    private Text registrationText;
    @FXML
    private Text homeText;
    @FXML
    private RadioButton maleButton;
    @FXML
    private RadioButton femaleButton;
    @FXML
    private RadioButton nogenderButton;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Text profileText;
    @FXML
    void registerButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(registerButton, string + "s/" + string + "sHome", true);
    }
    @FXML
    void profileButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"ProfileSelection", "Launcher");
    }
    @FXML
    private void homeButtonAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,"WeTrainGUI", "Launcher");
    }
    @FXML
    void registrationTextAction() throws IOException {
        PageSwitchSimple.switchPage(mainPane,string + "Registration", "Launcher");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        nogenderButton.setToggleGroup(group);
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
    }
}
