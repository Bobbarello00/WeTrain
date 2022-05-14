package exception.invalid_data_exception;

public class InvalidCardInfoException extends InvalidDataException{

    public InvalidCardInfoException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "The inserted card info isn't valid.",
                "Be sure that the inserted card number or date is correct."
        );
    }
}
