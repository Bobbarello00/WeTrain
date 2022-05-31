package exceptions;

public class UrlNotInsertedYetException extends PersonalizedException{

    public UrlNotInsertedYetException() {
        super(
                "SORRY...",
                "Url not inserted yet",
                "Wait for the trainer to start the lesson and try again later."
        );
    }
}
