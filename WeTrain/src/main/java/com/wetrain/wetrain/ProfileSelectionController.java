package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProfileSelectionController {

    @FXML
    private Button athletesButt;

    @FXML
    private Text homeButt;

    @FXML
    private ImageView logo;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button nutritionistsButt;

    @FXML
    private Button trainersButt;

    @FXML
    protected void athletesButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("AthleteRegistration");
        mainPane.setCenter(view);
    }
    @FXML
    protected void athletesButtonEntered(){
        athletesButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    protected void athletesButtonExited() {
        athletesButt.setStyle("-fx-background-color:  rgba(24, 147, 21, 0.65); -fx-background-radius: 25");
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    protected void nutritionistsButtonEntered(){
        nutritionistsButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    protected void nutritionistsButtonExited() {
        nutritionistsButt.setStyle("-fx-background-color:  rgba(24, 147, 21, 0.65); -fx-background-radius: 25");
    }
    @FXML
    protected void nutritionistButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistRegistration");
        mainPane.setCenter(view);
    }
    @FXML
    protected void trainersButtonEntered(){
        trainersButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    protected void trainersButtonExited() {
        trainersButt.setStyle("-fx-background-color:  rgba(24, 147, 21, 0.65); -fx-background-radius: 25");
    }
    @FXML
    protected void trainersButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainerRegistration");
        mainPane.setCenter(view);
    }
}
