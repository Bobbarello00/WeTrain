package exceptions.invalid_data_exception;

public class InvalidIbanException extends InvalidDataException{

    public InvalidIbanException() {
        super(
                "WRONG IBAN!",
                "Couldn't verify your iban",
                "Sorry for the inconvenience, be sure to write your IBAN correctly."
        );
    }
}
