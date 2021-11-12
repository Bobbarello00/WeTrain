package com.wetrain.wetrain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageSwitchSimple {
    private Pane view;

    public Pane getPage(String fileName) throws IOException {
        try{
            URL fileUrl = WeTrain.class.getResource(fileName + ".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("non ho trovato il file FXML");
            }
            view = FXMLLoader.load(fileUrl);
        } catch (FileNotFoundException e) {
            System.out.println("File "+fileName+" non trovato, controllare il PageSwitchSimple!");
        }
        return view;
    }
}
