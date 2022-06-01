package exceptions.invalid_data_exception;

public class InvalidTimeException extends InvalidDataException{

    public InvalidTimeException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "The start time of a lesson is bigger than the end time.",
                "Please correct the error."
        );
    }
}
