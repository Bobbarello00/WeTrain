package exception;

import database.DatabaseConnectionSingleton;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.PageSwitchSizeChange;
import viewone.engeneering.AlertFactory;

import java.io.IOException;

public class DBUnreachableException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("OOPS, CONNECTION TO DATABASE TIMED OUT!",
                "Can't reach WeTrainDB",
                "Check your internet connection and try again... you will be logged off");
    }

    public void alertAndLogOff() {
        alert();
        logOff();
    }

    public void logOff() {
        try {
            PageSwitchSizeChange.pageSwitch((Stage) MainPane.getInstance().getScene().getWindow(), "Login", "Launcher", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
