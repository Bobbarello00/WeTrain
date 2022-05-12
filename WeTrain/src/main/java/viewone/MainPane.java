package viewone;

import exception.runtime_exception.MainPaneNotExistException;
import javafx.scene.layout.BorderPane;

public class MainPane {

    private static BorderPane pane;

    private MainPane(){}

    public static synchronized void setInstance(BorderPane newPane){
        pane = newPane;
    }

    public static synchronized BorderPane getInstance(){
        if(pane == null){
            throw new MainPaneNotExistException();
        }
        return pane;
    }
}
