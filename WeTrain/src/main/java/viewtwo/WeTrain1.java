package viewtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewone.MainPane;

import java.io.IOException;
import java.util.Objects;

public class WeTrain1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainPane1 = new FXMLLoader(WeTrain1.class.getResource("MainPane1.fxml"));
        Scene scene = new Scene(mainPane1.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("WeTrainStyle1.css")).toExternalForm());
        stage.setTitle("WeTrain1");
        stage.getIcons().add(new Image("file:src/main/resources/viewone/images/WeTrainLogo.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        PageSwitchSimple1.switchPage(MainPane.getInstance(), "WeTrainGUI", "Launcher");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}