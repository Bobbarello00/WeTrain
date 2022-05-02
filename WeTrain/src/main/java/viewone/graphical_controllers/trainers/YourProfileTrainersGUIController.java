package viewone.graphical_controllers.trainers;

import controller.ProfileManagementController;
import exception.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.bean.*;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileTrainersGUIController extends ProfileGUIController implements Initializable {
    @FXML private TextField newIban;

    TrainerBean trainer;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML private void editConfirmation() {
        if(!Objects.equals(newIban.getText(), "")) {
            try{
                IbanBean ibanBean = new IbanBean(moneyLabel.getText());
                profileManagementController.updateTrainerIban(ibanBean);
                trainer = (TrainerBean) LoggedUserSingleton.getInstance();
                editPane.setDisable(true);
                editPane.setVisible(false);
                setIbanLabel();
                editButton.setDisable(false);
                editButton.setVisible(true);
                moneyLabel.setVisible(true);
            } catch (SQLException e){
                e.printStackTrace();
                //TODO GESTIONE EXCEPTION
            } catch (InvalidDataException e) {
                e.alert();
            } catch (DBConnectionFailedException e) {
                ((Stage) editButton.getScene().getWindow()).close();
                e.alertAndLogOff();
            }
        }
    }

    private void setIbanLabel() {
    }

    @FXML protected void editIbanButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        moneyLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainer = (TrainerBean) getLoggedUser();
        emailLabel.setText("Email: " + trainer.getEmail());
        firstNameLabel.setText(trainer.getName());
        lastNameLabel.setText(trainer.getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + trainer.getFiscalCode());
        moneyLabel.setText("Iban: " + trainer.getIban());
    }
}
