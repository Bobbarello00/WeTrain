package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class AthletesHomeController {
    @FXML
    private Text athletesHomeButt;
    @FXML
    private Button bookButt;
    @FXML
    private Button buyButt;
    @FXML
    private Button dietButt;
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button startButt;
    @FXML
    private Button editButt;
    @FXML
    private Button workoutButt;
    @FXML
    void athletesHomeButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("AthletesHome");
        mainPane.setCenter(view);
    }
    @FXML
    void bookButtonAction() {System.out.println("Book private lesson");}
    @FXML
    void bookButtonEntered() {bookButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void bookButtonExited() {bookButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void buyButtonAction() {System.out.println("Buy Monthly subscription");}
    @FXML
    void buyButtonEntered() {buyButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void buyButtonExited() {buyButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void dietButtonAction() {System.out.println("Request diet plan");}
    @FXML
    void dietButtonEntered() {dietButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void dietButtonExited() {dietButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void editButtonAction() {System.out.println("Edit Button");}
    @FXML
    void editButtonEntered() {editButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void editButtonExited() {editButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchAfterLog.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void logoutButtonEntered() {logoutButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void logoutButtonExited() {logoutButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void startButtonAction() {System.out.println("Start Workout");}
    @FXML
    void startButtonEntered() {startButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void startButtonExited() {startButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void workoutButtonAction() {System.out.println("Request workout plan");}
    @FXML
    void workoutButtonEntered() {workoutButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void workoutButtonExited() {workoutButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
}
