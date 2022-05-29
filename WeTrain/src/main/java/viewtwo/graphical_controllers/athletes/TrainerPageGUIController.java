package viewtwo.graphical_controllers.athletes;

import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.EmailFormGUIController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TrainerPageGUIController {
    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void findTrainerButtonAction() {
        //PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void sendEmailButtonAction() throws IOException {
        EmailFormGUIController controller = (EmailFormGUIController) PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            try {
                controller.setBackPathAndReceiver("TrainerPage", "athletes", LoggedUserSingleton.getAthleteAndTrainer().get(1));
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
                PageSwitchSimple.logOff();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML void yourTrainerButtonAction() throws IOException {
        //PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }
}