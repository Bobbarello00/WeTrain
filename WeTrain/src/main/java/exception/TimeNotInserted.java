package exception;

import javafx.scene.control.Alert;

public class TimeNotInserted extends NumberFormatException{

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("OOPS, SOMETHING WENT WRONG!");
        alert.setHeaderText("Warning.");
        alert.setContentText("Fill all time boxes.");
        alert.showAndWait();
    }
}
