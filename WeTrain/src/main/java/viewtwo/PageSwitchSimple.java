package viewtwo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import viewtwo.graphical_controllers.MainPane;

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
                fileUrl = WeTrain.class.getResource(fileName + EXTENSION);
            }
            else {
                fileUrl = WeTrain.class.getResource(pathString + "/" + fileName + EXTENSION);
            }
            if(fileUrl==null){
                throw new FileNotFoundException("FXML non trovato");
            }
            FXMLLoader root = new FXMLLoader();
            Pane view = root.load();
            MainPane.getInstance().setCenter(view);
            return root.getController();
        } catch (FileNotFoundException e) {
            System.out.println("Non trovo il File "+ pathString + "/" + fileName + EXTENSION + ", controllare il PageSwitchSimple1!");
        }
        return null;
    }

}
