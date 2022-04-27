package viewone;

import database.DatabaseConnectionSingleton;
import exception.DBConnectionFailedException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private static Pane view;
    private static Pane menu;
    private static final String EXTENSION = ".fxml";

    private PageSwitchSimple(){}

    public static Object switchPage(BorderPane mainPane, String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = WeTrain.class.getResource(fileName + EXTENSION);
            }
            else {
                fileUrl = WeTrain.class.getResource(pathString + "/" + fileName + EXTENSION);
            }
            if(fileUrl==null){
                throw new FileNotFoundException("Non ho trovato il file FXML");
            }
            FXMLLoader root = new FXMLLoader(fileUrl);
            try {
                view = root.load();
                mainPane.setCenter(view);
                return root.getController();
            } catch (LoadException e) {
                System.out.println("I'm here");
                DatabaseConnectionSingleton.deleteInstance();
                new DBConnectionFailedException().alertAndLogOff();
                return null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File "+ pathString + "/" + fileName + EXTENSION + " non trovato, controllare il PageSwitchSimple!");
            return null;
        }
    }

    public static void switchPageAndMenu(BorderPane mainPane, String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            URL menuUrl;
            fileUrl = WeTrain.class.getResource(pathString + "/" + fileName + EXTENSION);
            menuUrl = WeTrain.class.getResource(pathString + "/Menu" + pathString + EXTENSION);
            if(fileUrl==null || menuUrl==null){
                throw new FileNotFoundException("Non ho trovato il file FXML");
            }
            view = FXMLLoader.load(fileUrl);
            menu = FXMLLoader.load(menuUrl);
            mainPane.setCenter(view);
            mainPane.setLeft(menu);
        } catch (FileNotFoundException e) {
            System.out.println("File "+ pathString + "/" + fileName + EXTENSION +" non trovato, controllare il PageSwitchSimple!");
        }
    }
}
