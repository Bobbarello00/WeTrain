package exception;

import javafx.stage.Stage;
import viewone.MainPane;
import viewone.PageSwitchSizeChange;
import viewone.engeneering.AlertFactory;

import java.io.IOException;

public class DBConnectionFailedException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("OOPS, FAILED TO CONNECT TO DB!",
                "Can't establish connection with DB",
                "Check your internet connection and try again... you will be logged off");
    }

    public void alertAndLogOff() {
        AlertFactory.newWarningAlert("OOPS, FAILED TO CONNECT TO DB!",
                "Can't establish connection with DB",
                "Check your internet connection and try again... you will be logged off");
        try {
            PageSwitchSizeChange.pageSwitch((Stage) MainPane.getInstance().getScene().getWindow(), "Login", "Launcher", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
