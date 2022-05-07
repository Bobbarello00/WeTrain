package exception;

import viewone.engeneering.AlertFactory;

public class BrowserException extends Exception{

    public void alert() {
        AlertFactory.newWarningAlert("BROWSING ERROR",
                "Browsing not supported",
                "Sorry for the inconvenience but you can't access this web resource");
    }
}
