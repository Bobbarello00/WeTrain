package viewone.graphical_controllers;

import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public abstract class HomeControllerTrainersNutritionists extends HomeController {

    @Override
    protected void editButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "YourProfileTrainersNutritionists", "", false);
    }
}
