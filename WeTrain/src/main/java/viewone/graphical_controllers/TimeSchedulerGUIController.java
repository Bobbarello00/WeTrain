package viewone.graphical_controllers;

import exception.TimeNotInserted;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TimeSchedulerGUIController implements Initializable {
    @FXML private ComboBox<String> endHourBox;
    @FXML private ComboBox<String> endMinuteBox;
    @FXML private ComboBox<String> startHourBox;
    @FXML private ComboBox<String> startMinuteBox;
    @FXML private HBox timeSchedulerHBox;

    /*TODO logica per leggere l'orario
    @FXML private void select(ActionEvent event){
        ComboBox box = (ComboBox) event.getSource();
        String s = box.getSelectionModel().getSelectedItem().toString();
    }
    */


    public LocalTime getEndTime() {
        return getLocalTime(endHourBox, endMinuteBox);
    }

    public LocalTime getStartTime() {
        return getLocalTime(startHourBox, startMinuteBox);
    }

    private LocalTime getLocalTime(ComboBox<String> hourBox, ComboBox<String> minuteBox) {
        String hourString = hourBox.getSelectionModel().getSelectedItem();
        int hour;
        if(hourString != null) {
            hour = Integer.parseInt(hourString);
        } else {
            throw new TimeNotInserted();
        }
        String minuteString = minuteBox.getSelectionModel().getSelectedItem();
        int minute;
        if(minuteString != null) {
            minute = Integer.parseInt(minuteString);
        } else {
            throw new TimeNotInserted();
        }
        return LocalTime.of(hour, minute);
    }

    public void toggleVisibility(boolean toggle){
        timeSchedulerHBox.setVisible(toggle);
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> hoursList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        /*ObservableList<String> minutesList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
                                                                                        "24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47",
                                                                                        "48","49","50","51","52","53","54","55","56","57","58","59");*/
        ObservableList<String> minutesList = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");

        startHourBox.setItems(hoursList);
        startMinuteBox.setItems(minutesList);
        endHourBox.setItems(hoursList);
        endMinuteBox.setItems(minutesList);
    }
}
