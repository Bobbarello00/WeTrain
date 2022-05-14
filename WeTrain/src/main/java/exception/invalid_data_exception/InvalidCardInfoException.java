package exception.invalid_data_exception;

import viewone.engeneering.AlertFactory;

public class InvalidCardInfoException extends InvalidDataException{

    public ExpiredCardException() {
        TITLE = "OOPS, SOMETHING WENT WRONG!";
        HEADER = "The card inserted is expired.";
        CONTENT = "Be sure that the date inserted is correct or insert another card.";
    }

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The inserted card info isn't valid.",
                "Be sure that the inserted card number or date is correct.");
    }
}
