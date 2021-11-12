package com.wetrain.wetrain;

import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PasswordBehaviorActivation {
    private static int once = 0;

    public static void passwordFieldBind(TextField passwSField, PasswordField passwField, CheckBox checkVisible){
        if (once == 0) {
            passwSField.managedProperty().bind(checkVisible.selectedProperty());
            passwSField.visibleProperty().bind(checkVisible.selectedProperty());
            passwField.managedProperty().bind(checkVisible.selectedProperty().not());
            passwField.visibleProperty().bind(checkVisible.selectedProperty().not());
            passwSField.textProperty().bindBidirectional(passwField.textProperty());
            once = 1;
        }
    }
}
