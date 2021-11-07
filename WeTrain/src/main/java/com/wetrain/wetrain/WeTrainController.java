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
    @FXML
    protected void registerButtonEntered(){
        regButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void registerButtonExited(){
        regButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
    /*@FXML
    protected void signInButtonEntered(){
        signInText.setStyle("-fx-text-fill: rgb(20, 130, 17)");
    }
    @FXML
    protected void signInButtonExited(){
        signInText.setStyle("-fx-text-fill: rgba(24, 147, 21, 1);");
    }*/
}