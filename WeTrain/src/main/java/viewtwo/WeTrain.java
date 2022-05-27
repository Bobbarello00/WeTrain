package viewtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import viewtwo.graphical_controllers.MainPane;

import java.io.IOException;

public class WeTrain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainPane = new FXMLLoader(WeTrain.class.getResource("MainPane.fxml"));
        Scene scene = new Scene(mainPane.load());
        stage.setTitle("WeTrain - BasicLauncher");
        stage.getIcons().add(new Image("file:src/main/resources/viewone/images/WeTrainLogo.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        PageSwitchSimple.switchPage(MainPane.getInstance(), "WeTrainGUI", "launcher");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}