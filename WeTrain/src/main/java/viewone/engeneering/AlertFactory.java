package viewone.engeneering;

import javafx.scene.control.Alert;

public class AlertFactory {

    private AlertFactory() {}

    public static void newWarningAlert(String alertTitle, String alertHeaderText, String alertContentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(alertTitle!=null) {
            alert.setTitle(alertTitle);
        }
        if(alertHeaderText!=null) {
            alert.setHeaderText(alertHeaderText);
        }
        if(alertContentText!=null) {
            alert.setContentText(alertContentText);
        }
        alert.showAndWait();
    }
}
