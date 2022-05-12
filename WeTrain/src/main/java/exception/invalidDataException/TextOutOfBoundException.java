package exception.invalidDataException;

import viewone.engeneering.AlertFactory;

public class TextOutOfBoundException extends InvalidDataException {

    public void alert() {
        AlertFactory.newWarningAlert("WARNING!",
                "The text inserted is too long.",
                "Be sure to write only essential info. The limit is 450 character.");
    }
}
