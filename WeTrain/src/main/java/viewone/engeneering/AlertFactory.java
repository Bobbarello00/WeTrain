package viewone.engeneering;

import javafx.scene.control.Alert;

public class AlertFactory {

    private AlertFactory() {}

    public static void newWarningAlert(String alertTitle, String alertHeaderText, String alertContentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        newAlert(alert, alertTitle, alertHeaderText, alertContentText);
    }

    private static void newAlert(Alert alert, String alertTitle, String alertHeaderText, String alertContentText) {
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertContentText);

        alert.showAndWait();
    }

    public static void newInformationAlert(String alertTitle, String alertContentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        newAlert(alert, alertTitle, null, alertContentText);
    }
}
