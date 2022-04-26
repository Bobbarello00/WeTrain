package viewone.graphical_controllers.trainers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class YourProfileTrainersGUIController extends ProfileGUIController implements Initializable {
    @FXML private Label ibanLabel;

    @FXML protected void editIbanButtonAction(){
        //TODO implementazione cambio iban
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = getLoggedUser();
        emailLabel.setText("Email: " + userBean.getEmail());
        firstNameLabel.setText(userBean.getName());
        lastNameLabel.setText(userBean.getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + userBean.getFiscalCode());
        ibanLabel.setText("Iban: " + ((TrainerBean) userBean).getIban());
    }
}
