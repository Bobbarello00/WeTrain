package viewone.graphical_controllers.athletes;

import controller.LoginController;
import model.Athlete;
import model.Trainer;
import viewone.PageSwitchSizeChange;
import viewone.graphical_controllers.HomeGUIController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public abstract class HomeGUIControllerAthletes extends HomeGUIController {
    @Override
    protected void editButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "YourProfileAthletes", "athletes", false);
    }
}
