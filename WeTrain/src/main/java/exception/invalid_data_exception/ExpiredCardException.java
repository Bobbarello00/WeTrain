package exception.invalid_data_exception;

public class ExpiredCardException extends InvalidDataException {

    public ExpiredCardException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "The card inserted is expired.",
                "Be sure that the date inserted is correct or insert another card."
        );
    }
}
