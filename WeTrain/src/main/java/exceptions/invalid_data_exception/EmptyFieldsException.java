package exceptions.invalid_data_exception;

public class EmptyFieldsException extends InvalidDataException {

    public EmptyFieldsException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "Empty fields.",
                "Be sure to fill all fields, thanks for your collaboration!"
        );
    }
}
