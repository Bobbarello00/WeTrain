package exception.invalid_data_exception;

import viewone.engeneering.AlertFactory;

public class TimeNotInsertedException extends InvalidDataException {

    public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Warning.",
                "Fill all time boxes.");
    }
}
