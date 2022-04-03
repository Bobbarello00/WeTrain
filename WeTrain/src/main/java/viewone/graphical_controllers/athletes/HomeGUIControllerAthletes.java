package viewone.graphical_controllers.athletes;

import viewone.PageSwitchSizeChange;
import viewone.graphical_controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public abstract class HomeGUIControllerAthletes extends HomeController  {
    @Override
    protected void editButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "YourProfileAthletes", "athletes", false);
    }
}
