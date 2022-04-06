package viewone.graphical_controllers.athletes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Athlete;
import model.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileController;

import java.net.URL;
import java.util.ResourceBundle;

public class YourProfileAthletesGUIController extends ProfileController implements Initializable {
    @FXML private Label emailLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label fiscalCodeLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label paymentMethodLabel;
    @FXML private Button editButton;
    @FXML private Pane editPane;
    @FXML private TextField newCardNumber;
    @FXML private DatePicker newExpirationDate;

    @FXML private void editConfirmation(){
        //TODO CONTROLLI DI FORMATO E UNICITA + CALL DI UPDATE DATI CARTA
        editPane.setDisable(true);
        editPane.setVisible(false);
        setPaymentMethodLabel();
        editButton.setDisable(false);
        editButton.setVisible(true);
        paymentMethodLabel.setVisible(true);
    }

    @FXML private void editAbort(){
        editPane.setDisable(true);
        editPane.setVisible(false);
        editButton.setDisable(false);
        editButton.setVisible(true);
        paymentMethodLabel.setVisible(true);
    }

    @FXML private void editProfileButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        emailLabel.setText("Email: " + LoggedUserSingleton.getInstance().getEmail());
        firstNameLabel.setText(LoggedUserSingleton.getInstance().getName());
        lastNameLabel.setText(LoggedUserSingleton.getInstance().getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + LoggedUserSingleton.getInstance().getFiscalCode());
        setPaymentMethodLabel();
    }

    private void setPaymentMethodLabel(){
        if(((Athlete)LoggedUserSingleton.getInstance()).getCardNumber() == null){
            paymentMethodLabel.setText("Card: " + "Not inserted yet");
        } else {
            paymentMethodLabel.setText("Card: " + ((Athlete) LoggedUserSingleton.getInstance()).getCardNumber());
        }
    }
}
