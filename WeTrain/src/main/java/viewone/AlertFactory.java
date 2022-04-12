package viewone;

import javafx.scene.control.Alert;

public class AlertFactory {

    private AlertFactory() {}

    public static void newWarningAlert(String alertTitle, String alertHeaderText, String alertContentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(alertTitle!=null) {
            alert.setTitle(alertTitle);
        }else if(alertHeaderText!=null) {
            alert.setHeaderText(alertHeaderText);
        }else if(alertContentText!=null) {
            alert.setContentText(alertContentText);
        }
        alert.showAndWait();
    }
}
