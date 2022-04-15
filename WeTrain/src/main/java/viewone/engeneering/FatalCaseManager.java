package viewone.engeneering;

import controller.LoginController;
import controller.ProfileManagementController;
import model.Athlete;
import model.User;

import java.sql.SQLException;

public class FatalCaseManager {

    private FatalCaseManager() {}

    private static final ProfileManagementController profileManagementController = ProfileManagementController.getInstance();
    private static final LoginController loginController = LoginController.getInstance();

    public static void killApplication() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fatal Error",
                "Application will close.");
        System.exit(1);
    }

    public static void erasePaymentMethod() throws SQLException {
        User user = loginController.getLoggedUser();
        if(user instanceof Athlete) {
            profileManagementController.removeCardInfo((Athlete) user);
            AlertFactory.newWarningAlert("WARNING",
                    "SOMETHING WENT WRONG IN YOUR PAYMENT METHOD!",
                    "Your payment method has been removed.");
        } else {
            System.out.println("FatalCaseManager - erasePaymentMethod usata da un non atleta.");
            killApplication();
        }
    }
}
