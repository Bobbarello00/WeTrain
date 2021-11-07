package com.wetrain.wetrain;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class WeTrainController{
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button regButt = new Button("Register");
    @FXML
    protected void registerButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("ProfileSelection");
        mainPane.setCenter(view);
    }
    @FXML
    private Text signInText = new Text("Sign in");
    @FXML
    protected void signInTextAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("Login");
        mainPane.setCenter(view);
    }
    @FXML
    protected void registerButtonEntered(){
        regButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void registerButtonExited() {
        regButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
    @FXML
    protected void signInButtonEntered(){
        signInText.setStyle("-fx-fill: rgb(20, 130, 17)");
    }
    @FXML
    protected void signInButtonExited(){
       signInText.setStyle("-fx-fill: rgba(24, 147, 21, 1);");
    }
    @FXML
    private Text HomeButt;
    @FXML
    private ImageView Logo;
    //LOGIN PAGE --------------------------------------------------------------------------
    @FXML
    private Button submitButt;
    @FXML
    protected void HomeButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    protected void LogoAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    protected void submitButtonAction() {
        System.out.println("Submit effettuato!");
    }
    //PROFILE SELECTION PAGE --------------------------------------------------------------------
    @FXML
    private Button AthleteButt;
    @FXML
    private Button NutritionistButt;
    @FXML
    private Button TrainersButt;
    @FXML
    protected void AthleteButtonAction() {

    }
    @FXML
    protected void NutritionistButtonAction() {

    }
    @FXML
    protected void TrainersButtonAction() {

    }
}