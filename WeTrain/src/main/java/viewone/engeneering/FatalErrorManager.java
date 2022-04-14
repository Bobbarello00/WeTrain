package viewone.engeneering;

import viewone.AlertFactory;

public class FatalErrorManager{

    private FatalErrorManager() {}

    public static void kill() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fatal Error",
                "Application will close.");
        System.exit(1);
    }
}
