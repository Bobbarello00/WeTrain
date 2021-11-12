package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class NutritionistsHomeController {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Text nutritionistsHomeButt;
    @FXML
    private Button recordButt;
    @FXML
    private Button submitButt;
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    void logoutButtonEntered() {logoutButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void logoutButtonExited() {logoutButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void nutritionistsHomeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome");
        mainPane.setCenter(view);
    }
    @FXML
    void recordButtonAction() {System.out.println("Record Video Presentation");}
    @FXML
    void recordButtonEntered() {recordButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void recordButtonExited() {recordButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void submitButtonAction() {System.out.println("Submit Diet Plan");}
    @FXML
    void submitButtonEntered() {submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void submitButtonExited() {submitButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
}
