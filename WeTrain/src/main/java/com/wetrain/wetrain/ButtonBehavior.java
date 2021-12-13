package com.wetrain.wetrain;

import javafx.scene.control.Button;

import java.io.IOException;

public class ButtonBehavior {
    private static Button selectedButton;

    public void setBehavior(Button button, String filename, String path) throws IOException {
        if(selectedButton != button) {
            if (selectedButton != null) {
                selectedButton.setStyle(null);
            }
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24, 147, 21);" +
                    " -fx-border-color: rgb(24, 147, 21);" +
                    " -fx-border-radius: 50;");
            selectedButton = button;
            PageSwitchSimple.switchPage(MainPane.getInstance(), filename, path);
        } else {
            button.setStyle(null);
            selectedButton = null;
            PageSwitchSimple.switchPage(MainPane.getInstance(), "TrainersHome", path);
        }
    }
}
