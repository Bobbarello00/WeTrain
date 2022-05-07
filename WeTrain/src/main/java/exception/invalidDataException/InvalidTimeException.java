package exception.invalidDataException;

import viewone.engeneering.AlertFactory;

public class InvalidTimeException extends InvalidDataException{
    @Override
    public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The start time of a lesson is bigger than the end time.",
                "Please correct the error.");
    }
}
