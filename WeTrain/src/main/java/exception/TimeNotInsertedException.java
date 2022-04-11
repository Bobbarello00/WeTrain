package exception;

import viewone.AlertFactory;

public class TimeNotInsertedException extends InvalidDataException{

    public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Warning.",
                "Fill all time boxes.");
    }
}
