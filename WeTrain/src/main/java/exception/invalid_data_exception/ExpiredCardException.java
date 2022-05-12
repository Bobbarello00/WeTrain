package exception.invalid_data_exception;

import viewone.engeneering.AlertFactory;

public class ExpiredCardException extends InvalidDataException {

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The card inserted is expired.",
                "Be sure that the date inserted is correct or insert another card.");
    }
}
