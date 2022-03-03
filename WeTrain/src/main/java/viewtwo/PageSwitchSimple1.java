package viewtwo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import viewone.WeTrain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple1 {
    private static Pane view;
    private static Pane menu;
    private static final String EXTENSION = ".fxml";

    private PageSwitchSimple1(){}

    public static void switchPage(BorderPane mainPane, String fileName, String pathString) throws IOException {
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
            view = FXMLLoader.load(fileUrl);
        } catch (FileNotFoundException e) {
            System.out.println("File "+ pathString + "/" + fileName + EXTENSION + " non trovato, controllare il PageSwitchSimple!");
        }
        mainPane.setCenter(view);
    }
}
