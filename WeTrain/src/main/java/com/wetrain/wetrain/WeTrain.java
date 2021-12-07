package com.wetrain.wetrain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WeTrain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homePage = new FXMLLoader(WeTrain.class.getResource("Launcher/WeTrainGUI.fxml"));
        Scene scene = new Scene(homePage.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("WeTrainStyle.css")).toExternalForm());
        PageSwitchSizeChange.pageLauncher(stage, scene);
    }

   public static void main(String[] args) {
        launch(args);
    }
}