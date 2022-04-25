package exception;

import viewone.engeneering.AlertFactory;

public class DBConnectionFailedException extends Throwable{
    public void alert() {
        AlertFactory.newWarningAlert("OOPS, FAILED TO CONNECT TO DB!",
                "Can't establish connection with DB",
                "Check your internet connection and try again... you will be logged off");
    }
}
