package viewone;

import javafx.scene.layout.BorderPane;

public class MainPane {

    private static BorderPane pane;

    private MainPane(){}

    public synchronized static void setInstance(BorderPane newPane){
        pane = newPane;
    }

    public static BorderPane getInstance(){
        if(pane == null){
            System.out.println("Error: mainPane is null");
        }
        return pane;
    }
}
