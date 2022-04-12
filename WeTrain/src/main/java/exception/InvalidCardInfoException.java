package exception;

import viewone.AlertFactory;

public class InvalidCardInfoException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The inserted card info isn't valid.",
                "Be sure that the inserted card number or date is correct.");
    }
}
