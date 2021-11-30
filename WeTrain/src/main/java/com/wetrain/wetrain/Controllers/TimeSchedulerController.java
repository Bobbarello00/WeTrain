package com.wetrain.wetrain.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class TimeSchedulerController {
    private boolean toggle = false;
    @FXML
    private ComboBox<?> endHourBox;
    @FXML
    private ComboBox<?> endMinuteBox;
    @FXML
    private ComboBox<?> startHourBox;
    @FXML
    private ComboBox<?> startMinuteBox;
    @FXML
    private HBox timeSchedulerHBox;

    public void toggleVisibility(){
        timeSchedulerHBox.setVisible(!toggle);
        toggle= !toggle;
    }

}
