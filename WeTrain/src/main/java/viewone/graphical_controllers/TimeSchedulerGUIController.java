package viewone.graphical_controllers;

import exceptions.invalid_data_exception.TimeNotInsertedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class TimeSchedulerGUIController implements Initializable {
    @FXML private ChoiceBox<String> endHourBox;
    @FXML private ChoiceBox<String> endMinuteBox;
    @FXML private ChoiceBox<String> startHourBox;
    @FXML private ChoiceBox<String> startMinuteBox;
    @FXML private HBox timeSchedulerHBox;

    private String castValue(int time) {
        if(time < 10) {
            return "0" + time;
        }
        return String.valueOf(time);
    }

    public void setEndBox(LocalTime endTime) {
        this.endHourBox.setValue(castValue(endTime.getHour()));
        this.endMinuteBox.setValue(castValue(endTime.getMinute()));
    }

    public void setStartBox(LocalTime startTime) {
        this.startHourBox.setValue(castValue(startTime.getHour()));
        this.startMinuteBox.setValue(castValue(startTime.getMinute()));
    }

    public LocalTime getEndTime() throws TimeNotInsertedException {
        return getLocalTime(endHourBox, endMinuteBox);
    }

    public LocalTime getStartTime() throws TimeNotInsertedException {
        return getLocalTime(startHourBox, startMinuteBox);
    }

    private LocalTime getLocalTime(ChoiceBox<String> hourBox, ChoiceBox<String> minuteBox) throws TimeNotInsertedException {
        String hourString = hourBox.getSelectionModel().getSelectedItem();
        int hour;
        if(!Objects.equals(hourString, "h")) {
            hour = Integer.parseInt(hourString);
        } else {
            throw new TimeNotInsertedException();
        }
        String minuteString = minuteBox.getSelectionModel().getSelectedItem();
        int minute;
        if(!Objects.equals(minuteString, "min")) {
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

        setBox(startHourBox, "h", hoursList);
        setBox(startMinuteBox, "min", minutesList);
        setBox(endHourBox, "h", hoursList);
        setBox(endMinuteBox, "min", minutesList);
    }

    private void setBox(ChoiceBox<String> startHourBox, String startString, ObservableList<String> hoursList) {
        startHourBox.setValue(startString);
        startHourBox.setItems(hoursList);
    }
}
