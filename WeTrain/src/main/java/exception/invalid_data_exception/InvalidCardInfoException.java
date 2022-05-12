package exception.invalid_data_exception;

import viewone.engeneering.AlertFactory;

public class InvalidCardInfoException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The inserted card info isn't valid.",
                "Be sure that the inserted card number or date is correct.");
    }
}
