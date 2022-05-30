package viewtwo;

import engeneering.LoggedUserSingleton;
import engeneering.MainPane;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.FatalErrorException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private static final String EXTENSION = ".fxml";
    public static final String SRC_MAIN_RESOURCES_VIEWTWO = "src/main/resources/viewtwo/";
    public static final String SLASH = "/";

    private PageSwitchSimple(){}

    public static Object switchPage(String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWTWO + fileName + EXTENSION).toURI().toURL();
            }
            else {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWTWO + pathString + SLASH + fileName + EXTENSION).toURI().toURL();
            }
            FXMLLoader root = new FXMLLoader(fileUrl);
            Pane view = root.load();
            MainPane.getInstance().setCenter(view);
            return root.getController();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (LoadException e) {
            if(e.getCause() instanceof DBUnreachableException) {
                logOff();
            } else {
                e.printStackTrace();
                throw new FatalErrorException();
            }
        }
        return null;
    }

    public static void logOff() {
        try {
            LoggedUserSingleton.resetUserInfo();
            switchPage("Login", "launcher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
