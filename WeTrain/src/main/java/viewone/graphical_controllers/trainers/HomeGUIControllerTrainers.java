package viewone.graphical_controllers.trainers;

import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import viewone.graphical_controllers.HomeGUIController;

import java.io.IOException;

public abstract class HomeGUIControllerTrainers extends HomeGUIController {

    @Override public void editButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "YourProfileTrainers", "trainers", false);
    }
}
