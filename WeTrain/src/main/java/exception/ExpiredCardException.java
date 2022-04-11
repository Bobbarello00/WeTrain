package exception;

import viewone.AlertFactory;

public class ExpiredCardException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "The card inserted is expired.",
                "Be sure that the date inserted is correct or insert another card.");
    }
}
