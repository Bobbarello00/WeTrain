package com.wetrain.wetrain;

import javafx.scene.control.Button;

import java.io.IOException;

public class ButtonBehavior {
    private Button selectedButton;

    private Button getSelectedButton(){
        return this.selectedButton;
    }

    private void setSelectedButton(Button button){
        selectedButton = button;
    }

    public void resetSelectedButton(){
        if(this.getSelectedButton() != null) {
            this.getSelectedButton().setStyle(null);
            this.setSelectedButton(null);
        }
    }

    public void setBehavior(Button button, String filename, String path) throws IOException {
        Button oldButton = getSelectedButton();
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
            button.setStyle(null);
            setSelectedButton(null);
            PageSwitchSimple.switchPage(MainPane.getInstance(), path + "Home", path);
        }
    }
}
