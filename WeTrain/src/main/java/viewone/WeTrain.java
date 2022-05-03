package viewone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WeTrain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("javafx.sg.warn", "true");
        FXMLLoader mainPane = new FXMLLoader(WeTrain.class.getResource("MainPane.fxml"));
        Scene scene = new Scene(mainPane.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("WeTrainStyle.css")).toExternalForm());
        PageSwitchSizeChange.pageLauncher(stage, scene);
        PageSwitchSimple.switchPage(MainPane.getInstance(), "WeTrainGUI", "launcher");
        stage.show();
    }

   public static void main(String[] args) {
        launch(args);
    }
}