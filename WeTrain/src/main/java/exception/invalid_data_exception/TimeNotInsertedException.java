package exception.invalid_data_exception;

public class TimeNotInsertedException extends InvalidDataException {

    public TimeNotInsertedException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "Warning.",
                "Fill all time boxes."
        );
    }
}
