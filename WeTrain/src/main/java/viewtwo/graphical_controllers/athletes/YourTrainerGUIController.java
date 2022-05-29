package viewtwo.graphical_controllers.athletes;

import controller.SubscribeToTrainerController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import exception.invalid_data_exception.InvalidIbanException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import viewone.bean.TrainerBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourTrainerGUIController implements Initializable {

    @FXML private VBox trainerActions;
    @FXML private TextArea trainerDataTextArea;

    private TrainerBean trainer;
    private final SubscribeToTrainerController subscribeToTrainerController = new SubscribeToTrainerController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainerPage", "trainers");
    }

    @FXML void sendEmailButtonAction() {

    }

    @FXML void unsubscribeButtonAction() {

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            trainer = subscribeToTrainerController.getTrainer();
            if(trainer != null){
                trainerActions.setDisable(false);
                trainerDataTextArea.setText(String.format(
                        """
                        Name: %s
                                            
                        Surname: %s
                                            
                        Username: %s
                                            
                        Birth: %s
                                            
                        Email: %s
                                            
                        FC: %s
                        """,
                        trainer.getName(),
                        trainer.getSurname(),
                        trainer.getUsername(),
                        trainer.getBirth().toString(),
                        trainer.getEmail(),
                        trainer.getFiscalCode())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (InvalidIbanException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }
}
