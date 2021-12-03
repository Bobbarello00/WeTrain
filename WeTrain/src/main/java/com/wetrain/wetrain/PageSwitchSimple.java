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
            URL fileUrl = WeTrain.class.getResource(pathString + "/" + fileName + ".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("non ho trovato il file FXML");
            }
            view = FXMLLoader.load(fileUrl);
        } catch (FileNotFoundException e) {
            System.out.println("File "+fileName+" non trovato, controllare il PageSwitchSimple!");
        }
        mainPane.setCenter(view);
    }
}
