package exception;

public class BrowsingNotSupportedException extends PersonalizedException{

    public BrowsingNotSupportedException() {
        super(
                "BROWSING ERROR",
                "Failed to browse to the lesson url",
                "Sorry for the inconvenience but you can't access this web resource"
        );
    }
}
