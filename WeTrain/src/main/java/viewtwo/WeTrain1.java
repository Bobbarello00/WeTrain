package viewtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import viewone.MainPane;
import viewtwo.graphical_controllers.PageSwitchSimple;

import java.io.IOException;

public class WeTrain1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainPane = new FXMLLoader(WeTrain1.class.getResource("MainPane.fxml"));
        Scene scene = new Scene(mainPane.load());
        stage.setTitle("WeTrain - BasicLauncher");
        stage.getIcons().add(new Image("file:src/main/resources/viewone/images/WeTrainLogo.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        PageSwitchSimple.switchPage(MainPane.getInstance(), "WeTrainGUI1", "launcher1");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}