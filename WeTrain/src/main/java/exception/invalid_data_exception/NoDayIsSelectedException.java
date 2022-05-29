package exception.invalid_data_exception;

public class NoDayIsSelectedException extends InvalidDataException{

    public NoDayIsSelectedException() {
        super(
                "OOPS... SOMETHING WENT WRONG!",
                "NO DAY IS SELECTED",
                "Select a day.");
    }
}
