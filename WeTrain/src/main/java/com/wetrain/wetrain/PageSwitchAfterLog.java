package com.wetrain.wetrain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PageSwitchAfterLog {

    public static void pageSwitch(Button button, String page) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource(page + ".fxml")));
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(Objects.requireNonNull(WeTrain.class.getResource("WeTrainStyle.css")).toExternalForm());
        newStage.setTitle("WeTrain");
        stage.getIcons().add(new Image("file:src/main/resources/Images/WeTrainLogo.png"));
        newStage.setScene(newScene);
        newStage.setResizable(false);
        newStage.show();
    }

}
