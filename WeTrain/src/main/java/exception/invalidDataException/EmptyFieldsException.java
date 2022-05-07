package exception.invalidDataException;

import exception.invalidDataException.InvalidDataException;
import viewone.engeneering.AlertFactory;

public class EmptyFieldsException extends InvalidDataException {

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Empty fields.",
                "Be sure to fill all fields, thanks for your collaboration!");
    }
}
