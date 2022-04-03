package viewone.graphical_controllers.athletes;

import javafx.fxml.Initializable;
import model.Athlete;
import model.LoggedUserSingleton;
import model.Trainer;
import viewone.graphical_controllers.ProfileController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class YourProfileAthletesGUIController extends ProfileController implements Initializable {
    @FXML
    private Label emailLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label fiscalCodeLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label paymentMethodLabel;
    @FXML
    protected void editPaymentButtonAction(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailLabel.setText("Email: " + LoggedUserSingleton.getInstance().getEmail());
        firstNameLabel.setText(LoggedUserSingleton.getInstance().getName());
        lastNameLabel.setText(LoggedUserSingleton.getInstance().getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + LoggedUserSingleton.getInstance().getFiscalCode());
        if(((Athlete)LoggedUserSingleton.getInstance()).getCardNumber() == null){
            paymentMethodLabel.setText("Card: " + "Not inserted yet");
        } else {
            paymentMethodLabel.setText("Card: " + ((Athlete) LoggedUserSingleton.getInstance()).getCardNumber());
        }
    }
}
