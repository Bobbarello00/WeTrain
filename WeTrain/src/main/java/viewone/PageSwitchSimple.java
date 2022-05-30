package viewone;

import exceptions.DBUnreachableException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {

    private static final String EXTENSION = ".fxml";
    public static final String SRC_MAIN_RESOURCES_VIEWONE = "src/main/resources/viewone/";
    public static final String SLASH = "/";

    private PageSwitchSimple() {}

    public static Object switchPage(BorderPane mainPane, String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + fileName + EXTENSION).toURI().toURL();
            }
            else {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + pathString + SLASH + fileName + EXTENSION).toURI().toURL();
            }
            FXMLLoader root = new FXMLLoader(fileUrl);
            return setViewAndGetController(mainPane, root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable private static Object setViewAndGetController(BorderPane mainPane, FXMLLoader root) throws IOException {
        try {
            Pane view = root.load();
            mainPane.setCenter(view);
            return root.getController();
        } catch (LoadException e) {
            if(e.getCause() instanceof DBUnreachableException){
                PageSwitchSizeChange.logOff();
            } else {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void switchPageAndMenu(BorderPane mainPane, String fileName, String pathString) throws IOException {
        URL fileUrl;
        URL menuUrl;
        fileUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + pathString + SLASH + fileName + EXTENSION).toURI().toURL();
        menuUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + pathString + "/Menu" + pathString + EXTENSION).toURI().toURL();
        Pane view = FXMLLoader.load(fileUrl);
        Pane menu = FXMLLoader.load(menuUrl);
        mainPane.setCenter(view);
        mainPane.setLeft(menu);
    }
}
