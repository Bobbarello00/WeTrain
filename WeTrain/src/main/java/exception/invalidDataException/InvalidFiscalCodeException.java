package exception.invalidDataException;

import viewone.engeneering.AlertFactory;

public class InvalidFiscalCodeException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fiscal code not valid",
                "Be sure to fill all fields correctly, thanks for your collaboration!");
    }
}
