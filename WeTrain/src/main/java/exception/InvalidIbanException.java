package exception;

import viewone.engeneering.AlertFactory;

public class InvalidIbanException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("WRONG IBAN!",
                "Couldn't verify your iban",
                "Sorry for the inconvenience, be sure to write your IBAN correctly.");
    }
}
