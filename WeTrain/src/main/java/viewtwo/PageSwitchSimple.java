package viewtwo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import viewtwo.graphical_controllers.MainPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private static Pane view;
    private static final String EXTENSION = ".fxml";

    private PageSwitchSimple(){}

    public static void switchPage(String fileName, String pathString) throws IOException {
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
            view = FXMLLoader.load(fileUrl);
        } catch (FileNotFoundException e) {
            System.out.println("Non trovo il File "+ pathString + "/" + fileName + EXTENSION + ", controllare il PageSwitchSimple1!");
        }
        MainPane.getInstance().setCenter(view);
    }
}
