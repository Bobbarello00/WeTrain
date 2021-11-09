package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TrainersHomeController {
    @FXML
    private Button calendarButt;
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button modifyButt;
    @FXML
    private Button recordButt;
    @FXML
    private Button startButt;
    @FXML
    private Button submitButt;
    @FXML
    private Text trainersHomeButt;
    @FXML
    void calendarButtonAction() {System.out.println("Lesson Plan Calendar");}
    @FXML
    void calendarButtonEntered() {calendarButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void calendarButtonExited() {calendarButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void logoutButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    void logoutButtonEntered() {logoutButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void logoutButtonExited() {logoutButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void modifyButtonAction() {System.out.println("Modify Lessons");}
    @FXML
    void modifyButtonEntered() {modifyButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void modifyButtonExited() {modifyButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void recordButtonAction() {System.out.println("Record Video Presentation");}
    @FXML
    void recordButtonEntered() {recordButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void recordButtonExited() {recordButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void startButtonAction() {System.out.println("Start Lesson");}
    @FXML
    void startButtonEntered() {startButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void startButtonExited() {startButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void submitButtonAction() {System.out.println("Submit Workout Plan");}
    @FXML
    void submitButtonEntered() {submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");}
    @FXML
    void submitButtonExited() {submitButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");}
    @FXML
    void trainersHomeButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("TrainersHome");
        mainPane.setCenter(view);
    }
}
