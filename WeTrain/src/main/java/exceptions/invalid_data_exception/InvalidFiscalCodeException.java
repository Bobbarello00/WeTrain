package exceptions.invalid_data_exception;

public class InvalidFiscalCodeException extends InvalidDataException{

    public InvalidFiscalCodeException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "Fiscal code not valid",
                "Be sure to fill all fields correctly, thanks for your collaboration!"
        );
    }
}
