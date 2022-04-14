package exception;

import viewone.engeneering.AlertFactory;

public class InvalidBirthException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Birth date not valid.",
                "Be sure to fill all fields correctly, thanks for your collaboration!");
    }
}
