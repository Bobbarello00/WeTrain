package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class MoreInfoController {
    private static int once = 0;
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
        once = 0;
        PageSwitchSizeChange.pageSwitch(registerButton, string + "s/" + string + "sHome", true);
    }
    @FXML
    protected void profileButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ProfileSelection", "Launcher");
        once = 0;
        mainPane.setCenter(view);
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI", "Launcher");
        once = 0;
        mainPane.setCenter(view);
    }
    @FXML
    protected void toggleRadioGroup(){
        if(once==0) {
            ToggleGroup group = new ToggleGroup();
            nogenderButton.setToggleGroup(group);
            maleButton.setToggleGroup(group);
            femaleButton.setToggleGroup(group);
            once=1;
        }
    }
    @FXML
    public void registrationTextAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage(string + "Registration", "Launcher");
        once = 0;
        mainPane.setCenter(view);
    }
}
