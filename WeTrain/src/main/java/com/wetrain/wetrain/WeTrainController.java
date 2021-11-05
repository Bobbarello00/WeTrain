package com.wetrain.wetrain;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class WeTrainController {
    @FXML
    private Button regButt = new Button("Register");
    @FXML
    protected void registerButtonAction(){
        System.out.println("Register");
    }
    @FXML
    private Text signInText = new Text("Sign in");
    @FXML
    protected void signInTextAction(){
        System.out.println("SignIn");
    }
}