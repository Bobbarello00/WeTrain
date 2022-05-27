package viewtwo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import viewtwo.graphical_controllers.MainPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private static final String EXTENSION = ".fxml";

    private PageSwitchSimple(){}

    public static Object switchPage(String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = new File("src/main/resources/viewtwo/" + fileName + EXTENSION).toURI().toURL();
            }
            else {
                fileUrl = new File("src/main/resources/viewtwo/"  + pathString + "/" + fileName + EXTENSION).toURI().toURL();
            }
            FXMLLoader root = new FXMLLoader(fileUrl);
            Pane view = root.load();
            MainPane.getInstance().setCenter(view);
            return root.getController();
        } catch (FileNotFoundException e) {
            System.out.println("Non trovo il File src/main/resources/viewtwo/" + pathString + "/" + fileName + EXTENSION + ", controllare il PageSwitchSimple1!");
        }
        return null;
    }

    public static void logOff() throws IOException {
        switchPage("Login", "launcher");
    }
}
