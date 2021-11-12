package com.wetrain.wetrain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WeTrain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homePage = new FXMLLoader(WeTrain.class.getResource("WeTrainGUI.fxml"));
        Scene scene = new Scene(homePage.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("WeTrainStyle.css")).toExternalForm());
        stage.setTitle("WeTrain");
        stage.getIcons().add(new Image("file:src/main/resources/Images/WeTrainLogo.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
   public static void main(String[] args) {
        launch(args);
    }
}