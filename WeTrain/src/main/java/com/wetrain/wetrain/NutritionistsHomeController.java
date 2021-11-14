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
    private Button editButt;
    @FXML
    private Button createButt;
    @FXML
    private Button startButt;
    @FXML
    private Button manageRButt;
    @FXML
    private Button manageDButt;
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
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
    void startButtonAction() {
        System.out.println("Start Appointment");
    }
    @FXML
    void startButtonEntered() {
        startButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");
    }
    @FXML
    void startButtonExited() {
        startButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    void manageRButtonAction() {
        System.out.println("Manage Requests");
    }
    @FXML
    void manageRButtonEntered() {
        manageRButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");
    }
    @FXML
    void manageRButtonExited() {
        manageRButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    void manageDButtonAction() {
        System.out.println("Manage Your Diets");
    }
    @FXML
    void manageDButtonEntered() {
        manageDButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");
    }
    @FXML
    void manageDButtonExited() {
        manageDButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    void editButtonAction() {
        System.out.println("Edit");
    }
    @FXML
    void editButtonEntered() {
        editButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");
    }
    @FXML
    void editButtonExited() {
        editButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }
    @FXML
    void createButtonAction() {
        System.out.println("Create New Diet");
    }
    @FXML
    void createButtonEntered() {
        createButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 25");
    }
    @FXML
    void createButtonExited() {
        createButt.setStyle("-fx-background-color: rgb(24, 147, 21); -fx-background-radius: 25");
    }

}
