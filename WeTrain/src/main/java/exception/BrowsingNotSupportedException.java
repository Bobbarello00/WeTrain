package exception;

import viewone.engeneering.AlertFactory;

public class BrowsingNotSupportedException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("BROWSING ERROR",
                "Failed to browse to the lesson url",
                "Sorry for the inconvenience but you can't access this web resource");
    }
}
