package com.wetrain.wetrain;

import javafx.scene.layout.BorderPane;

public class MainPane {
    private static BorderPane mainPane;

    public static void setInstance(BorderPane pane){
        mainPane = pane;
    }

    public static BorderPane getInstance(){
        if(mainPane == null){
            System.out.println("Error: mainPane is null");
        }
        return mainPane;
    }
}
