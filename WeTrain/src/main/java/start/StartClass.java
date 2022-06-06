package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class StartClass extends Application {

    @Override public void start(Stage primaryStage) throws Exception {
        System.setProperty("javafx.sg.warn", "true");
        URL url = new File("src/main/resources/Start.fxml").toURI().toURL();
        FXMLLoader root = new FXMLLoader(url);
        Pane view = root.load();
        primaryStage.setScene(new Scene(view));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
