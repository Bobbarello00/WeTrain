package exception;

import javafx.stage.Stage;
import viewone.AlertFactory;
import viewone.MainPane;

public class FatalErrorManager{

    public static void kill() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fatal Error",
                "Application will close.");
        System.exit(1);
    }
}
