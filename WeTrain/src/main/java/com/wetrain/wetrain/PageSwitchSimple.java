package com.wetrain.wetrain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private static Pane view;

    public static void switchPage(BorderPane mainPane, String fileName, String pathString) throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = WeTrain.class.getResource(fileName + ".fxml");
            }
            else {
                fileUrl = WeTrain.class.getResource(pathString + "/" + fileName + ".fxml");
            }
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("Non ho trovato il file FXML");
            }
            view = FXMLLoader.load(fileUrl);
        } catch (FileNotFoundException e) {
            System.out.println("File "+ pathString + "/" + fileName + ".fxml" + " non trovato, controllare il PageSwitchSimple!");
        }
        mainPane.setCenter(view);
    }
}
