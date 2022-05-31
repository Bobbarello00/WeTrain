package exceptions.invalid_data_exception;

public class InvalidBirthException extends InvalidDataException{

    public InvalidBirthException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "Birth date not valid.",
                "Be sure to fill all fields correctly, thanks for your collaboration!"
        );
    }
}
