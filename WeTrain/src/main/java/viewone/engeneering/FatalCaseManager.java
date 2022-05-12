package viewone.engeneering;

import controller.LoginController;
import controller.ProfileManagementController;
import exception.DBUnreachableException;
import model.Athlete;
import model.User;

import java.sql.SQLException;

public class FatalCaseManager {

    private FatalCaseManager() {}

    private static final ProfileManagementController profileManagementController = new ProfileManagementController();
    private static final LoginController loginController = new LoginController();

    public static void killApplication() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fatal Error",
                "Application will close.");
        System.exit(1);
    }

    public static void erasePaymentMethod() throws SQLException, DBUnreachableException {
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
