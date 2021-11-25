package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {
    private static int once = 0;
    public static String string;
    @FXML
    private static Label dynamicLabel;
    @FXML
    private Button registerButt;
    @FXML
    private Text homeButt;
    @FXML
    private RadioButton maleButt;
    @FXML
    private RadioButton femaleButt;
    @FXML
    private RadioButton nogenderButt;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Text profileButt;
    @FXML
    void registerButtonAction() throws IOException {
        once = 0;
        PageSwitchSizeChange.pageSwitch(registerButt, "Athletes/AthletesHome");
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
            nogenderButt.setToggleGroup(group);
            maleButt.setToggleGroup(group);
            femaleButt.setToggleGroup(group);
            once=1;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dynamicLabel.setText(string);
    }
}
