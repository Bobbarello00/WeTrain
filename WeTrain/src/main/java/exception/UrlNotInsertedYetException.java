package exception;

import viewone.engeneering.AlertFactory;

public class UrlNotInsertedYetException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("SORRY...",
                "Url not inserted yet",
                "Wait for the trainer to start the lesson and try again later.");
    }
}
