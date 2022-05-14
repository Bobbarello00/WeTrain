package exception;

public class DBUnreachableException extends PersonalizedException{

    public DBUnreachableException() {
        super(
                "OOPS, CONNECTION TO DATABASE TIMED OUT!",
                "Can't reach WeTrainDB",
                "Check your internet connection and try again... you will be logged off"
        );
    }
}
