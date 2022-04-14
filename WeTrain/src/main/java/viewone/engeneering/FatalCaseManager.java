package viewone.engeneering;

public class FatalCaseManager {

    private FatalCaseManager() {}

    public static void killApplication() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fatal Error",
                "Application will close.");
        System.exit(1);
    }
}
