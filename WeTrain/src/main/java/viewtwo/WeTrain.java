package viewtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WeTrain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("javafx.sg.warn", "true");
        URL url = new File("src/main/resources/viewtwo/MainPane.fxml").toURI().toURL();
        FXMLLoader mainPane = new FXMLLoader(url);
        Scene scene = new Scene(mainPane.load());
        url = new File("src/main/resources/viewtwo/SimpleWeTrainStyle.css").toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
        stage.setTitle("WeTrain - BasicLauncher");
        stage.getIcons().add(new Image("file:src/main/resources/viewone/images/WeTrainLogo.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        PageSwitchSimple.switchPage("WeTrainGUI", "launcher");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}