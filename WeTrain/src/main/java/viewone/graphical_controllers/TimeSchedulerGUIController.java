package viewone.graphical_controllers;

import exception.TimeNotInsertedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TimeSchedulerGUIController implements Initializable {
    @FXML private ComboBox<String> endHourBox;
    @FXML private ComboBox<String> endMinuteBox;
    @FXML private ComboBox<String> startHourBox;
    @FXML private ComboBox<String> startMinuteBox;
    @FXML private HBox timeSchedulerHBox;

    public void setEndBox(LocalTime endTime) {
        this.endHourBox.setValue(String.valueOf(endTime.getHour()));
        this.endMinuteBox.setValue(String.valueOf(endTime.getMinute()));
    }

    public void setStartBox(LocalTime startTime) {
        this.startHourBox.setValue(String.valueOf(startTime.getHour()));
        this.startMinuteBox.setValue(String.valueOf(startTime.getMinute()));
    }

    public LocalTime getEndTime() throws TimeNotInsertedException {
        return getLocalTime(endHourBox, endMinuteBox);
    }

    public LocalTime getStartTime() throws TimeNotInsertedException {
        return getLocalTime(startHourBox, startMinuteBox);
    }

    private LocalTime getLocalTime(ComboBox<String> hourBox, ComboBox<String> minuteBox) throws TimeNotInsertedException {
        String hourString = hourBox.getSelectionModel().getSelectedItem();
        int hour;
        if(hourString != null) {
            hour = Integer.parseInt(hourString);
        } else {
            throw new TimeNotInsertedException();
        }
        String minuteString = minuteBox.getSelectionModel().getSelectedItem();
        int minute;
        if(minuteString != null) {
            minute = Integer.parseInt(minuteString);
        } else {
            throw new TimeNotInsertedException();
        }
        return LocalTime.of(hour, minute);
    }

    public void toggleVisibility(boolean toggle){
        timeSchedulerHBox.setVisible(toggle);
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> hoursList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        ObservableList<String> minutesList = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");

        startHourBox.setItems(hoursList);
        startMinuteBox.setItems(minutesList);
        endHourBox.setItems(hoursList);
        endMinuteBox.setItems(minutesList);
    }
}
