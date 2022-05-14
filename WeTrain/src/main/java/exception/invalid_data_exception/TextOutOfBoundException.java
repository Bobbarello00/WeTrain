package exception.invalid_data_exception;

public class TextOutOfBoundException extends InvalidDataException {

    public TextOutOfBoundException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "The text inserted is too long.",
                "Be sure to write only essential info. The limit is 450 character."
        );
    }
}
