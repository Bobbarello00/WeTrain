package viewone;

import engeneering.MainPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class PageSwitchSizeChange {

    private PageSwitchSizeChange() {}

    public static void logOff() {
        try {
            pageSwitch((Stage) MainPane.getInstance().getScene().getWindow(), "Login", "Launcher", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHome(Button button, String page, String path) throws IOException {
        Stage newStage;
        Parent root = FXMLLoader.load(new File("src/main/resources/viewone/MainPane.fxml").toURI().toURL());
        BorderPane pane = (BorderPane) root;

        newStage = new Stage();
        ((Stage) button.getScene().getWindow()).close();

        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(new File("src/main/resources/viewone/WeTrainStyle.css").toURI().toURL().toExternalForm());
        pageLauncher(newStage, newScene);
        PageSwitchSimple.switchPageAndMenu(pane, page, path);

        newStage.show();
    }

    public static Object pageSwitch(Button button, String page, String path, boolean closeOldStage) throws IOException {
        return pageSwitch((Stage) button.getScene().getWindow(), page, path, closeOldStage);
    }

    public static Object pageSwitch(Stage stage, String page, String path, boolean closeOldStage) throws IOException {
        Stage newStage;
        BorderPane actualPane = MainPane.getInstance();
        Parent root = FXMLLoader.load(new File("src/main/resources/viewone/MainPane.fxml").toURI().toURL());
        BorderPane pane = (BorderPane) root;

        if(!closeOldStage) {
            MainPane.setInstance(actualPane);
            MainPane.getInstance().setDisable(true);
            Alert alert = new Alert(Alert.AlertType.NONE);
            newStage = (Stage) alert.getDialogPane().getScene().getWindow();
        } else {
            newStage = new Stage();
            stage.close();
        }
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(new File("src/main/resources/viewone/WeTrainStyle.css").toURI().toURL().toExternalForm());
        pageLauncher(newStage, newScene);
        Object controller = PageSwitchSimple.switchPage(pane, page, path);
        if(controller != null) {
            newStage.show();
        }
        return controller;
    }

    static void pageLauncher(Stage newStage, Scene newScene) {
        newStage.setTitle("WeTrain");
        newStage.getIcons().add(new Image("file:src/main/resources/viewone/images/WeTrainLogo.png"));
        newStage.setScene(newScene);
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newScene.setFill(Color.TRANSPARENT);
    }
}
