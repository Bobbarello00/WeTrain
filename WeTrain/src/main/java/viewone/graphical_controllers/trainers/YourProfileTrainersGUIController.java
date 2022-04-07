package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.LoggedUserSingleton;
import model.Trainer;
import viewone.bean.TrainerBean;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class YourProfileTrainersGUIController extends ProfileGUIController implements Initializable {
    @FXML private Label emailLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label fiscalCodeLabel;
    @FXML private Label ibanLabel;

    @FXML protected void editIbanButtonAction(){

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            emailLabel.setText("Email: " + LoggedUserSingleton.getInstance().getEmail());
            firstNameLabel.setText(LoggedUserSingleton.getInstance().getName());
            lastNameLabel.setText(LoggedUserSingleton.getInstance().getSurname());
            fiscalCodeLabel.setText("FiscalCode: " + LoggedUserSingleton.getInstance().getFiscalCode());
            ibanLabel.setText("Iban: " + ((TrainerBean) LoggedUserSingleton.getInstance()).getIban());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
