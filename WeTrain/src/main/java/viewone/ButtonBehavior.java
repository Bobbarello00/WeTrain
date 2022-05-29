package viewone;

import engeneering.MainPane;
import javafx.scene.control.Button;

import java.io.IOException;

public class ButtonBehavior {
    private Button selectedButton;

    private void setSelectedButton(Button button){
        selectedButton = button;
    }

    public void resetSelectedButton(){
        if(selectedButton != null) {
            selectedButton.setStyle(null);
            setSelectedButton(null);
        }
    }

    public void setBehavior(Button button, String filename, String path) throws IOException {
        Button oldButton = selectedButton;
        if(oldButton != button) {
            if (oldButton != null) {
                oldButton.setStyle(null);
            }
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24, 147, 21);" +
                    " -fx-border-color: rgb(24, 147, 21);" +
                    " -fx-border-radius: 50;");
            setSelectedButton(button);
            PageSwitchSimple.switchPage(MainPane.getInstance(), filename, path);
        } else {
            resetSelectedButton();
            PageSwitchSimple.switchPage(MainPane.getInstance(), path + "Home", path);
        }
    }
}
